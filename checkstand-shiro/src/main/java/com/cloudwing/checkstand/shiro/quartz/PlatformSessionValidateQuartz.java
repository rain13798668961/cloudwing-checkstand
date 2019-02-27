package com.cloudwing.checkstand.shiro.quartz;


import com.cloudwing.checkstand.consumer.platform.UserService;
import com.cloudwing.checkstand.shiro.CustomShiroSessionDAO;
import com.cloudwing.checkstand.shiro.session.ShiroSessionRepository;
import com.yunyitg.rpc.base.exception.RpcException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//@Component
public class PlatformSessionValidateQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(PlatformSessionValidateQuartz.class);

    @Autowired
    private CustomShiroSessionDAO customShiroSessionDAO;

    @Autowired
    private ShiroSessionRepository shiroSessionRepository;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void validatePlatformSessions() {
        LOG.info("@Scheduled-------开始平台session验证任务");

        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
//        List<String> sessionIds = getPlatformSessionIds(sessions);
        Map<String, String> platformSessionidMapsessionId = getplatformSessionidMapSessionId(sessions);
        List<String> sessionids = new ArrayList(platformSessionidMapsessionId.keySet());
        try {
            if (sessionids.size() <= 0) {
                return;
            }
            List<com.cloudwing.checkstand.consumer.platform.beans.Session> platformSessions =
                    userService.authSessionIds(sessionids);

            Map<String, Integer> sessionidMapUserId = new HashMap<String, Integer>();
            for (com.cloudwing.checkstand.consumer.platform.beans.Session session : platformSessions) {
                sessionidMapUserId.put(session.getSessionId(), session.getOnlineStatus());
            }
            for (String sessionid : sessionids) {
                Integer userId = sessionidMapUserId.get(sessionid);

                if (0 >= userId) {  // 用户id小于或者等于0，表示用户在平台已注销登录
                    //在本系统注销登录
                    String sessionId = platformSessionidMapsessionId.get(sessionid);
                    if (sessionId != null) {
                        Session session = shiroSessionRepository.getSession(sessionId);
                        if (session != null) {
                            session.stop();
                        }
                    }
//                    customShiroSessionDAO.delete();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }


    }

    private List<String> getPlatformSessionIds(Collection<Session> sessions) {
        List<String> sessionIds = new ArrayList<String>();
        for (Session s : sessions) {
            Object id = s.getAttribute("sessionid");
            if (id != null) {
                sessionIds.add((String) id);
            }
        }
        return sessionIds;
    }

    private Map<String, String> getplatformSessionidMapSessionId(Collection<Session> sessions) {
        Map<String, String> map = new HashMap<String, String>();
        for (Session s : sessions) {
            Object sessionid = s.getAttribute("sessionid");
            if (sessionid != null) {
                map.put((String) sessionid, (String) s.getId());
            }
        }
        return map;
    }


}

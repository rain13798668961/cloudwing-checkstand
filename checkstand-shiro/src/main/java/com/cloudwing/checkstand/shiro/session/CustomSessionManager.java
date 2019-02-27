package com.cloudwing.checkstand.shiro.session;

import com.cloudwing.checkstand.shiro.CustomShiroSessionDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.*;

/**
 * 用户session 手动管理
 */
public class CustomSessionManager {

    /**
     * session status
     */
    public static final String SESSION_STATUS ="session-online-status";
    ShiroSessionRepository shiroSessionRepository;

    CustomShiroSessionDAO customShiroSessionDAO;

    /**
     * 获取所有的有效Session用户
     * @return
     */
    /*public  List<UserOnlineBo> getAllUser() {
        //获取所有session
        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
        List<UserOnlineBo> list = new ArrayList<UserOnlineBo>();

        for (Session session : sessions) {
            UserOnlineBo bo = getSessionBo(session);
            if(null != bo){
                list.add(bo);
            }
        }
        return list;
    }
    *//**
     * 根据ID查询 SimplePrincipalCollection
     * @param userIds	用户ID
     * @return
     *//*
    @SuppressWarnings("unchecked")
    public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Integer...userIds){
        //把userIds 转成Set，好判断
        Set<Integer> idset = (Set<Integer>) StringUtils.array2Set(userIds);
        //获取所有session
        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取SimplePrincipalCollection
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User){
                    User user = (User)obj;
                    //比较用户ID，符合即加入集合
                    if(null != user && idset.contains(user.getId())){
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }



    *//**
     * 获取单个Session
     * @param sessionId
     * @return
     */
     public Session getSession(String sessionId) {
         Session session = shiroSessionRepository.getSession(sessionId);
         return session;
     }

    /*
    public UserOnlineBo getSession(String sessionId) {
        Session session = shiroSessionRepository.getSession(sessionId);
        UserOnlineBo bo = getSessionBo(session);
        return bo;
    }
    private UserOnlineBo getSessionBo(Session session){
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if(null == obj){
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if(obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            *//**
             * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中
             * return new SimpleAuthenticationInfo(user,user.getPswd(), getName());的user 对象。
             *//*
            obj = spc.getPrimaryPrincipal();
            if(null != obj && obj instanceof User){
                //存储session + user 综合信息
                UserOnlineBo userBo = new UserOnlineBo((User)obj);
                //最后一次和系统交互的时间
                userBo.setLastAccess(session.getLastAccessTime());
                //主机的ip地址
                userBo.setHost(session.getHost());
                //session ID
                userBo.setSessionId(session.getId().toString());
                //session最后一次与系统交互的时间
                userBo.setLastLoginTime(session.getLastAccessTime());
                //回话到期 ttl(ms)
                userBo.setTimeout(session.getTimeout());
                //session创建时间
                userBo.setStartTime(session.getStartTimestamp());
                //是否踢出
                SessionStatus sessionStatus = (SessionStatus)session.getAttribute(SESSION_STATUS);
                boolean status = Boolean.TRUE;
                if(null != sessionStatus){
                    status = sessionStatus.getOnlineStatus();
                }
                userBo.setSessionStatus(status);
                return userBo;
            }
        }
        return null;
    }

    *//**
     * 改变Session状态
     * @param status {true:激活,false:踢出}
     * @param userId
     * @return
     *//*
    public Map<String, Object> changeSessionStatus(Boolean status,
                                                   Integer userId) {
        //获取所有session
        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
        for (Session session : sessions) {

            //判断是否是当前Session，不能把自己踢出去了。
            if(TokenManager.getSession().getId().equals(session.getId())){
                continue;
            }


            //获取SimplePrincipalCollection
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User){
                    User user = (User)obj;
                    //比较用户ID，符合即加入集合
                    if(null != user && userId.equals(user.getId())){
                        return changeSessionStatus(status,session);
                    }
                }
            }
        }
        return  null ;
    }
    *//**
     * 改变Session状态
     * @param status {true:踢出,false:激活}
     * @param session
     * @return
     *//*
    public Map<String, Object> changeSessionStatus(Boolean status, Session session) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            SessionStatus sessionStatus = new SessionStatus();
            sessionStatus.setOnlineStatus(status);
            session.setAttribute(SESSION_STATUS, sessionStatus);
            //更新Session
            customShiroSessionDAO.update(session);
//			customShiroSessionDAO.delete(session);
            map.put("status", 200);
            map.put("sessionStatus", status?1:0);
            map.put("sessionStatusText", status?"踢出":"激活");
            map.put("sessionStatusTextTd", status?"有效":"已踢出");
            map.put("kickedSessionId", session.getId());
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "改变Session状态错误，sessionId[%s]", session.getId());
            map.put("status", 500);
            map.put("message", "改变失败，有可能Session不存在，请刷新再试！");
        }
        return map;

    }
    *//**
     * 改变Session状态
     * @param status {true:踢出,false:激活}
     * @param sessionId
     * @return
     *//*
    public Map<String, Object> changeSessionStatus(Boolean status,
                                                   String sessionId) {
        Session session = shiroSessionRepository.getSession(sessionId);
        return changeSessionStatus(status, session);
    }
    *//**
     * 查询要禁用的用户是否在线。
     * @param id		用户ID
     * @param status	用户状态
     *//*
    public void forbidUserById(Integer id, Long status) {
        //获取所有在线用户
        for(UserOnlineBo bo : getAllUser()){
            Integer userId = bo.getId();
            //匹配用户ID
            if(userId.equals(id)){
                //获取用户Session
                Session session = shiroSessionRepository.getSession(bo.getSessionId());
                //标记用户Session
                SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
                //是否踢出 true:有效，false：踢出。
                sessionStatus.setOnlineStatus(status.intValue() == 1);
                //更新Session
                customShiroSessionDAO.update(session);
            }
        }
    }
    *//**
     * 根据用户id，处理登录信息，（查找当前用户，之前有没有登录，并且踢出）
     * @param
     *//*
    //TODO 这里可以采取异步队列处理更优
    public void excuteLoginByUserId(Integer userId){

        Map<String, Object> result = changeSessionStatus(Boolean.FALSE, userId);
        if(null == result ){
            LoggerUtils.debug(getClass(), "没有查询到用户其他登录信息。");
        }else{
            LoggerUtils.fmtDebug(getClass(), "已踢出的用户：%s",JSON.toJSONString(result));
        }
    }*/

    public void setShiroSessionRepository(
            ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

    public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO) {
        this.customShiroSessionDAO = customShiroSessionDAO;
    }
}

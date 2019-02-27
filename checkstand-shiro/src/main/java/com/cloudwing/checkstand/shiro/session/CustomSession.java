package com.cloudwing.checkstand.shiro.session;

import com.cloudwing.checkstand.consumer.platform.UserService;
import com.yunyitg.rpc.base.exception.RpcException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 自定义session
 * 继承simpleSession
 * 重写validate() 方法
 */
public class CustomSession extends SimpleSession {

    protected static final String platformSessionid = "sessionid";


    private UserService userService;

    public CustomSession() {
        super();
    }

    @Override
    public void validate() throws InvalidSessionException {
        super.validate();

        if (this.isLogout()) {
            String msg = "Session with id [" + this.getId() + "] has been " + "explicitly stopped in cw platform.  No further interaction under this session is " + "allowed.";
            throw new StoppedSessionException(msg);
        }

    }

    /**
     * 判断该sessionid对应外贸圈平台的用户是否已下线
     * @return
     */
    protected boolean isLogout() {
        Object sessionid = this.getAttribute(platformSessionid);
        if (sessionid != null) {
            try {
                Integer userId = userService.authSessionId((String)sessionid);
                if (0 >= userId) {
                    return true;
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
            return false;
        } else {
            return true;
        }
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

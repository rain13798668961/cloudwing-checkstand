package com.cloudwing.checkstand.shiro.listener;


import com.cloudwing.checkstand.shiro.cache.impl.SessionShiroCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * shiro 会话监听器
 */
public class CustomSessionListener implements SessionListener {

    private static final Logger log = LoggerFactory.getLogger(CustomSessionListener.class);

    SessionShiroCacheManager sessionShiroCacheManager;

    /**
     * 一个会话生命周期的开始
     * @param session
     */
    @Override
    public void onStart(Session session) {
        log.debug("session 创建成功，SessionId[{}]", session.getId());
    }

    /**
     * 一个会话生命周期的结束
     * @param session
     */
    @Override
    public void onStop(Session session) {
        log.debug("session 销毁成功，SessionId[{}]", session.getId());
    }

    /**
     * 会话空闲期超时 删除会话，结束其生命周期
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        sessionShiroCacheManager.deleteSession(session.getId());
        log.debug("session 删除成功，SessionId[{}]", session.getId());

    }

    public SessionShiroCacheManager getSessionShiroCacheManager() {
        return sessionShiroCacheManager;
    }

    public void setSessionShiroCacheManager(SessionShiroCacheManager sessionShiroCacheManager) {
        this.sessionShiroCacheManager = sessionShiroCacheManager;
    }
}

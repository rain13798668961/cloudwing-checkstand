package com.cloudwing.checkstand.shiro.cache.impl;

import com.cloudwing.checkstand.shiro.cache.ShiroCacheManager;
import com.cloudwing.checkstand.shiro.session.CustomSessionManager;
import com.cloudwing.checkstand.shiro.session.SessionStatus;
import com.cloudwing.checkstand.shiro.session.ShiroSessionRepository;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

public class SessionShiroCacheManager<K, V> implements ShiroCacheManager, ShiroSessionRepository {

    private static final Logger log = LoggerFactory.getLogger(SessionShiroCacheManager.class);

    EhCacheManager ehCacheManager;

    @Override
    public Cache<Serializable, Session> getCache() {
        return ehCacheManager.getCache("shiro-activeSessionCache");
    }

    @Override
    public void destroy() {
        ehCacheManager.destroy();
    }

    @Override
    public void clear() {
        getCache().clear();
    }

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            SessionStatus sessionStatus = (SessionStatus) session.getAttribute(CustomSessionManager.SESSION_STATUS);
            //不存在才添加。
            if(null == sessionStatus){
                //Session 踢出自存存储。
                sessionStatus = new SessionStatus();
                session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
            }
            log.debug("当前SessionId：{}，状态为：{}", session.getId(),
                    sessionStatus.isOnlineStatus() ? "可用" : "不可用");
            getCache().put(session.getId(), session);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("save session error，id:{}", session.getId());
        }
    }

    @Override
    public void deleteSession(Serializable sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getCache().remove(sessionId);
        } catch (Exception e) {
            log.error("删除session出现异常，id:" + sessionId, e);
        }
    }

    @Override
    public Session getSession(Serializable sessionId) {
        return getCache().get(sessionId);
    }

    @Override
    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getCache().values();
        } catch (Exception e) {
            log.error("获取全部session异常", e);
        }

        return sessions;
    }

    public EhCacheManager getEhCacheManager() {
        return ehCacheManager;
    }

    public void setEhCacheManager(EhCacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }
}

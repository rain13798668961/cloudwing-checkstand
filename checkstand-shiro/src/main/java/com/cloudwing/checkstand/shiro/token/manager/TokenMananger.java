package com.cloudwing.checkstand.shiro.token.manager;

import com.alibaba.fastjson.JSON;
import com.cloudwing.checkstand.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * shiro 管理下的Token工具类
 */
public class TokenMananger {

    /**
     * 获取当前登录的用户User对象
     * @return
     */
    public static User getToken() {
        User token = null;
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (null != obj) {
            if (obj instanceof User) {
                token = (User) SecurityUtils.getSubject().getPrincipal();
            } else {
                token = JSON.parseObject(JSON.toJSON(obj).toString(), User.class);
            }
        }
        return token;
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Integer getUserId(){
        return getToken()==null?null:getToken().getId();
    }

    /**
     * 获取当前用户的Session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 把值放入到当前登录用户的Session里
     * @param key
     * @param value
     */
    public static void setVal2Session(Object key ,Object value){
        getSession().setAttribute(key, value);
    }
    /**
     * 判断是否登录
     * @return
     */
    public static boolean isLogin() {
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 清空当前用户权限信息。
     */
//    public static void clearNowUserAuth() {
//        realm.clearCachedAuthorizationInfo();
//    }

}

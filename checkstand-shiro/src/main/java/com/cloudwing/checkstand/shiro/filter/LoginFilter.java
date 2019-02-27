package com.cloudwing.checkstand.shiro.filter;

import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.shiro.utils.ShiroFilterUtils;
import com.cloudwing.checkstand.user.entity.User;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录拦截器
 */
public class LoginFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    private static final String appSessKey = "app_sess_key";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        User token = TokenMananger.getToken();

//        log.info("app_sess_key校验是否成功：" + isAppSessKeyMatchInCookieAndSession(servletRequest));
        if ((null != token /*&& isAppSessKeyMatchInCookieAndSession(servletRequest)*/) || isLoginRequest(servletRequest, servletResponse)) {
            return Boolean.TRUE;
        }
        if (ShiroFilterUtils.isAjax(servletRequest)) {
            Map<String, String> resultMap = new HashMap<String, String>();
            log.debug("当前用户没有登录，并且是Ajax请求！");
            resultMap.put("status", "30000");
            resultMap.put("msg", "用户登录已过期，请重新登录");
            ShiroFilterUtils.out(servletResponse, resultMap);
        }
        return Boolean.FALSE;
    }

    private boolean isAppSessKeyMatchInCookieAndSession(ServletRequest servletRequest) {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
//                log.info("cookie:" + cookies[i].getName() + ":" + cookies[i].getValue());
                if (cookies[i].getName().equals(appSessKey)) {
                    if (cookies[i].getValue().equals((String) TokenMananger.getSession().getAttribute("app_sess_key"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        if (! ShiroFilterUtils.isAjax(servletRequest)) {
            saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        }
        return Boolean.FALSE;
    }
}

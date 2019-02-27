package com.cloudwing.checkstand.shiro.filter;

import com.cloudwing.checkstand.shiro.utils.ShiroFilterUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户权限 校验拦截
 */
public class PermissionFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(PermissionFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {

        Subject subject = getSubject(servletRequest, servletResponse);
        if (null != mappedValue) {
            String[] arra = (String[])mappedValue;
            System.out.println(arra.toString());
//            LoggerUtils.fmtInfo(getClass(), "访问接口参数：", arra.toString());
            for (String permission : arra) {
                if(subject.isPermitted(permission)){
                    log.info("通过permission过滤器拦截");
                    return Boolean.TRUE;
                }
            }
        }
        HttpServletRequest httpRequest = ((HttpServletRequest)servletRequest);

        String uri = httpRequest.getRequestURI();//获取URI
        String basePath = httpRequest.getContextPath();//获取basePath
        if(null != uri && uri.startsWith(basePath)){
            uri = uri.replace(basePath, "");
            log.info("uri:" + uri);
        }
        if(subject.isPermitted(uri)){
            log.info("通过permission过滤器拦截");
            return Boolean.TRUE;
        }
        if(ShiroFilterUtils.isAjax(servletRequest)){
            Map<String,String> resultMap = new HashMap<String, String>();

            log.debug("当前用户没有操作权限，并且是Ajax请求！");
            resultMap.put("status", "30000");
            resultMap.put("msg", "当前用户没有操作权限");//当前用户没有登录！
            ShiroFilterUtils.out(servletResponse, resultMap);
        }
        log.info("未通过permission过滤器拦截");
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        /*Subject subject = getSubject(servletRequest, servletResponse);
        if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面
            // 保存Request和Response 到登录后的链接
            saveRequestAndRedirectToLogin(servletRequest, servletResponse);
            *//**	  上面替代了这2步
             * 	      saveRequest(request);
             * 		  WebUtils.issueRedirect(request, response,getLoginUrl());
             *//*
        } else {
            if (StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED_URL)) {//如果有未授权页面跳转过去


                String url = ShiroFilterUtils.UNAUTHORIZED_URL;
                WebUtils.issueRedirect(servletRequest, servletResponse, url);
            } else {//否则返回401未授权状态码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }*/
        if (! ShiroFilterUtils.isAjax(servletRequest)) {
            if (StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED_URL)) {//如果有未授权页面跳转过去
                String url = ShiroFilterUtils.UNAUTHORIZED_URL;
                WebUtils.issueRedirect(servletRequest, servletResponse, url);
            }
        }
        return false;
    }
}

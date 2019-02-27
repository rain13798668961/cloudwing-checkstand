package com.cloudwing.checkstand.shiro.utils;

import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Shiro 工具类
 */
public class ShiroFilterUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(ShiroFilterUtils.class);

    //踢出URL
    public  static final String KICKOUT_URL = "/out/kickedOut.html";
    //没有权限URL
    public static final String UNAUTHORIZED_URL = "/out/unauthorized.html";



    /**
     *
     * @描述：判断请求是否是ajax
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            logger.debug("shiro工具类【ShiroFilterUtils.isAjax】当前请求,为Ajax请求");
            return Boolean.TRUE;
        }
        logger.debug("shiro工具类【ShiroFilterUtils.isAjax】当前请求,非Ajax请求");
        return Boolean.FALSE;
    }

    /**
     *
     * @描述：response输出json
     * @创建人：wyait
     * @创建时间：2018年4月24日 下午5:14:22
     * @param response
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, String> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");//设置编码
            response.setContentType("application/json");//设置返回类型
            out = response.getWriter();
            out.println(FastJsonUtils.collectToString(resultMap));//输出
//            logger.error("响应json信息成功");
        } catch (Exception e) {
            logger.error("响应json信息出错", e);
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

}

package com.cloudwing.checkstand.shiro.config;

import com.cloudwing.checkstand.shiro.CustomShiroSessionDAO;
import com.cloudwing.checkstand.shiro.cache.impl.SessionShiroCacheManager;
import com.cloudwing.checkstand.shiro.filter.LoginFilter;
import com.cloudwing.checkstand.shiro.filter.PermissionFilter;
import com.cloudwing.checkstand.shiro.listener.CustomSessionListener;
import com.cloudwing.checkstand.shiro.realm.UserRealm;
import com.cloudwing.checkstand.shiro.session.ShiroSessionRepository;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.*;

@Configuration
public class ShiroConfig {

    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        HashMap<String,Filter> hashMap=new HashMap<String,Filter>();
        hashMap.put("login", loginFilter());
        hashMap.put("permission", permissionFilter());

        shiroFilterFactoryBean.setFilters(hashMap);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/layuiadmin/**", "anon");
        filterChainDefinitionMap.put("/home", "login");
//        filterChainDefinitionMap.put("/ajax/**", "login");
//        filterChainDefinitionMap.put("/user/**", "login");
        filterChainDefinitionMap.put("/account/login", "anon");
        filterChainDefinitionMap.put("/account/getInitData", "login");

        filterChainDefinitionMap.put("/out/**", "anon");
        filterChainDefinitionMap.put("/index", "login,permission");
        filterChainDefinitionMap.put("/order/**", "login,permission");
        filterChainDefinitionMap.put("/mch/**", "login,permission");
        filterChainDefinitionMap.put("/office/**", "login,permission");
        filterChainDefinitionMap.put("/user/**", "login,permission");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");



        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * shiro安全管理器设置realm认证和ehcache缓存管理
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm());
        // //注入ehcache缓存管理器;
        securityManager.setCacheManager(ehCacheManager());
        // //注入session管理器;
        securityManager.setSessionManager(sessionManager());
        //注入Cookie记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份认证realm; (账号密码校验；角色、权限等)
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        return realm;
    }

    /**
     * ehcache缓存管理器；shiro整合ehcache：通过安全管理器：securityManager 单例的cache防止热部署重启失败
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        log.debug("=====shiro整合ehcache缓存：ShiroConfiguration.getEhCacheManager()");
        EhCacheManager ehcache = new EhCacheManager();
        CacheManager cacheManager = CacheManager.getCacheManager(null);
        if(cacheManager == null){
            try {

                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:config/ehcache-shiro.xml"));

            } catch (CacheException | IOException e) {
                e.printStackTrace();
            }
        }
        ehcache.setCacheManager(cacheManager);
        return ehcache;
    }

    /**
     * 设置记住我cookie过期时间
     * @return
     */
    @Bean
    public SimpleCookie remeberMeCookie(){
        log.debug("记住我，设置cookie过期时间！");
        //cookie名称;对应前端的checkbox的name = rememberMe
        SimpleCookie scookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间30天 ,单位秒  [10天]
        scookie.setMaxAge(864000);
        return scookie;
    }

    /**
     * 配置cookie记住我管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        log.debug("配置cookie记住我管理器！");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        return cookieRememberMeManager;
    }

    @Bean
    public QuartzSessionValidationScheduler2 sessionValidationScheduler(DefaultWebSessionManager sessionManager) {
        QuartzSessionValidationScheduler2 sessionValidationScheduler = new QuartzSessionValidationScheduler2();
        sessionValidationScheduler.setSessionValidationInterval(1800000);
//        sessionValidationScheduler.setSessionValidationInterval(120000);
        sessionValidationScheduler.setSessionManager(sessionManager);
        return sessionValidationScheduler;
    }

//    @Bean
//    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
//        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
//        sessionValidationScheduler.setInterval(1800000);
//        sessionValidationScheduler.setSessionManager(sessionManager());
//        return sessionValidationScheduler;
//    }


    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler(sessionManager));
        sessionManager.setSessionDAO(customShiroSessionDAO());
        sessionManager.setSessionListeners(sessionListeners());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }



    @Bean
    public CustomShiroSessionDAO customShiroSessionDAO() {
        CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
        customShiroSessionDAO.setShiroSessionRepository(sessionShiroCacheManager());
        customShiroSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return customShiroSessionDAO;
    }

    /**
     * 自定义cookie中session名称等配置
     * @return
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(180000);
        return sessionIdCookie;
    }

    @Bean
    public SessionShiroCacheManager sessionShiroCacheManager() {
        SessionShiroCacheManager sessionShiroCacheManager = new SessionShiroCacheManager();
        sessionShiroCacheManager.setEhCacheManager(ehCacheManager());
        return sessionShiroCacheManager;
    }

    @Bean
    public List<SessionListener> sessionListeners() {
        List<SessionListener> sessionListeners = new ArrayList<SessionListener>();
        sessionListeners.add(customSessionListener());
        return sessionListeners;
    }

    @Bean
    public CustomSessionListener customSessionListener() {
        CustomSessionListener listener = new CustomSessionListener();
        listener.setSessionShiroCacheManager(sessionShiroCacheManager());
        return listener;
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Bean
    public PermissionFilter permissionFilter() {
        return new PermissionFilter();
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(new Object[]{securityManager()});
        bean.setArguments(securityManager());
        return bean;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}

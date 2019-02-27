package com.cloudwing.checkstand.shiro.realm;

import com.cloudwing.checkstand.common.constant.Constant;
import com.cloudwing.checkstand.shiro.token.PlatformToken;
import com.cloudwing.checkstand.shiro.token.ShiroToken;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.service.UserService;
import com.yunyitg.rpc.base.exception.RpcException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 获取用户的角色和权限信息
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;


    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Integer userId = TokenMananger.getUserId();
        log.info("用户授权用户id:" + userId);
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();

        //根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roles = userService.listRolesByUserId(userId);
        info.setRoles(roles);
        TokenMananger.getToken().setRoles(roles);

        // 根据用户ID查询权限（permission），放入到Authorization里。
        Set<String> permissions = new HashSet<String>();
        Set<String> permUrls = userService.listPermissionsByUserId(TokenMananger.getToken());
        permUrls.remove(null);
        permissions.addAll(permUrls);
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        ShiroToken token = (ShiroToken) authenticationToken;
        User user = userService.login(token.getUsername(), token.getPswd());
        if (null == user) {
            throw new AccountException("账号或密码不正确！");
        } else if(Constant.S_ZERO.equals(user.getStatus())) {
            throw new DisabledAccountException("账号已经禁止登录！");
        } else {
            user.setUpdateAt(new Date());
            String ip = token.getIp();

            log.debug("登录成功，登录IP[%s] .", ip);
            userService.updateById(user);
        }
       /* PlatformToken token = (PlatformToken) authenticationToken;

        User user = null;
        try {
            user = userService.platformLogin(token.getUsername(), token.getPswd());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        if (null == user) {
            throw new AccountException("平台用户服务登录失败！");
        } else {
            user.setUpdateAt(new Date(System.currentTimeMillis()));
            user.setPassword(token.getPswd());
            String ip = token.getIp();

            log.info("登录成功，登录IP[[]] .", ip);
            userService.updateById(user);
            TokenMananger.setVal2Session("app_sess_key", token.getPswd());
            TokenMananger.setVal2Session("sessionid", user.getSessionid());
        }*/

        return new SimpleAuthenticationInfo(user,token.getPswd(), getName());
    }

    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}

package com.cloudwing.checkstand.web;

import com.alibaba.fastjson.JSONObject;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.consumer.platform.UserService;
import com.cloudwing.checkstand.permission.service.PermissionService;
import com.cloudwing.checkstand.permission.vo.InitData;
import com.cloudwing.checkstand.shiro.token.PlatformToken;
import com.cloudwing.checkstand.shiro.token.ShiroToken;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.user.entity.User;
import com.yunyitg.rpc.base.exception.RpcException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Controller
//@RequestMapping("/account")
public class LoginController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PermissionService permissionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account/login", method=RequestMethod.POST)
    @ResponseBody public BaseResult<User> doLogin(@RequestParam(value="account", required=true)String username,
                                    @RequestParam(value="password", required=true) String password, Boolean rememberMe) {
        LOG.info("invoke----------/ajax/user/login");
        Subject subject = SecurityUtils.getSubject();
        ShiroToken token = new ShiroToken(username, password);
        try {
            subject.login(token);
            return new BaseResult<User>("10000","登录成功");
        } catch (Exception e) {
            return new BaseResult<User>("20000", e.getMessage());
        }
    }


    @RequestMapping(value = "/account/logout", method=RequestMethod.POST)
    @ResponseBody public BaseResult<Object> doLogout() {
        LOG.info("invoke----------/account/logout");
        try {
            TokenMananger.logout();
            return new BaseResult<Object>("10000", "登出成功");
        } catch (Exception e) {
            return new BaseResult<Object>("20000", "登出出现错误," +e.getMessage());
        }
    }

    @RequestMapping(value = "/account/getInitData", method=RequestMethod.POST)
    @ResponseBody public BaseResult<InitData> getInitData() {
        InitData initData = new InitData();
        initData.setProjectName("收银台");
        initData.setProjectDesc("Powered By 佛山市云翼软件科技有限公司");
        initData.setUserName(TokenMananger.getToken().getName());
        initData.setMenus(permissionService.listMenusByUserId(TokenMananger.getToken()));
        return new BaseResult<InitData>("10000","操作成功",initData);

    }

    @GetMapping(value = "/account/toLogin")
    public String toLogin(@CookieValue(value = "app_sess_key", required = false) String app_sess_key, String token) {
        LOG.info("invoke----------/account/toLogin, token:" + token);
        LOG.info("app_sess_key:" +app_sess_key);

        Subject subject = SecurityUtils.getSubject();
        PlatformToken platformToken = new PlatformToken(token, app_sess_key);
        try {
            subject.login(platformToken);
            return "redirect:/out/redirect.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "/tips/unauthorized";
        }
    }
}

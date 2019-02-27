package com.cloudwing.checkstand.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/index",method=RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "home", method=RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/order/list.html", method=RequestMethod.GET)
    public String orderList() {
        return "order";
    }

    @RequestMapping(value = "/user/list.html", method=RequestMethod.GET)
    public String userList() {
        return "user";
    }

    @RequestMapping(value = "/mch/list.html", method=RequestMethod.GET)
    public String mchList() {
        return "mch";
    }

    @RequestMapping(value = "/office/list.html", method=RequestMethod.GET)
    public String officeList() {
        return "office";
    }

    @RequestMapping(value = "/out/error.html", method=RequestMethod.GET)
    public String errorHtml() {
        return "tips/error";
    }

    @RequestMapping(value = "/out/unauthorized.html", method=RequestMethod.GET)
    public String unauthorizedHtml() {
        return "/tips/unauthorized";
    }

    @RequestMapping(value = "/out/test.html", method=RequestMethod.GET)
    public String test() {
        return "/tips/test";
    }

}

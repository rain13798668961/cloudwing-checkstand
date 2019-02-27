package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserServiceTest extends BaseTest{

    @Autowired
    UserService userService;

    @Test
    public void test() {

        Set<String> s = userService.listRolesByUserId(2);
        System.out.println(s.toString());
    }
}

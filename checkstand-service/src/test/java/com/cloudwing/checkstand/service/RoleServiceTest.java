package com.cloudwing.checkstand.service;

import com.cloudwing.checkstand.mapper.BaseTest;
import com.cloudwing.checkstand.permission.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by cjf on 2019/1/25.
 */
public class RoleServiceTest extends BaseTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void roleDescriptionByUserIdTest() {
        String s = roleService.roleDescriptionByUserId(1);
        System.out.println(s);
    }
}

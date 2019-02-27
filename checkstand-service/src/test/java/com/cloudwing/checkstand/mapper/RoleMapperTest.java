package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.permission.entity.Role;
import com.cloudwing.checkstand.permission.mapper.RoleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create by cjf on 2019/1/24.
 */
public class RoleMapperTest extends BaseTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void listByUserId() {
        List<Role> roles = roleMapper.listByUserId(1);
        for (Role role : roles) {
            System.out.println(role.toString());
        }
    }
}

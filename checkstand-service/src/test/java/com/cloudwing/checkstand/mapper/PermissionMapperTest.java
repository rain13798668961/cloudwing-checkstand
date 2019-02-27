package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.permission.entity.Permission;
import com.cloudwing.checkstand.permission.mapper.PermissionMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionMapperTest extends BaseTest{

    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void testlistMenusByUserId() {

        List<Permission> l = permissionMapper.listMenusByUserId(1);
        System.out.println(l.size());
    }

    @Test
    public void testListUrlByUserId() {
        permissionMapper.listUrlByUserId(1);
    }
}

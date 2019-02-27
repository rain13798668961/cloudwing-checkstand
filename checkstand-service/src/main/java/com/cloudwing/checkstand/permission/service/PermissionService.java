package com.cloudwing.checkstand.permission.service;

import com.cloudwing.checkstand.permission.entity.Role;
import com.cloudwing.checkstand.permission.vo.Menu;
import com.cloudwing.checkstand.user.entity.User;

import java.util.List;

public interface PermissionService {

    /**
     * 根据用户id
     * @return
     */
    List<Menu> listMenusByUserId(User user);
}

package com.cloudwing.checkstand.permission.mapper;

import com.cloudwing.checkstand.permission.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}
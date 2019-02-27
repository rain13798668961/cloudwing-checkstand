package com.cloudwing.checkstand.user.mapper;

import com.cloudwing.checkstand.user.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
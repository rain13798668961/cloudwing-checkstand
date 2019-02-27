package com.cloudwing.checkstand.common.utils;

import com.cloudwing.checkstand.common.enums.RoleEnum;

import java.util.Set;

/**
 * 用户工具类
 */
public class UserHelper {
    /**
     * 是否拥有超级管理员角色
     * @param roles
     * @return
     */
    public static boolean hasSuperAdminRoles(Set<String> roles) {

        for (String role : roles) {
            if (RoleEnum.SuperAdmin.getCode().equals(role)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 是否拥有管理员角色
     * @return
     */
    public static boolean hasAdminRoles(Set<String> roles) {

        for (String role : roles) {
            if (RoleEnum.Admin.getCode().equals(role)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 是否拥有现场负责人角色
     * @return
     */
    public static boolean hasOfficeLeaderRoles(Set<String> roles) {

        for (String role : roles) {
            if (RoleEnum.OfficeLeader.getCode().equals(role)) {
                return true;
            }
        }
        return false;

    }


    /**
     * 是否拥有操作员/观察者角色
     * @param roles
     * @return
     */
    public static boolean hasOperatorRoles(Set<String> roles) {
        for (String role : roles) {
            if (RoleEnum.OfficeOperator.getCode().equals(role) || RoleEnum.Obeserver.getCode().equals(role)) {
                return true;
            }
        }
        return false;

    }
}

package com.cloudwing.checkstand.common.enums;

/**
 * 用户类型 枚举
 */
public enum  RoleEnum {
    SuperAdmin("88888888", "超级管理员"),
    Admin("10000001", "管理员"),
    OfficeLeader("20000001", "现场负责人"),
    OfficeOperator("20000002", "现场操作员"),
    Obeserver("10000003", "观察者")

    ;
    private RoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 角色类型代码
     */
    private String code;

    /**
     * 角色类型
     */
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

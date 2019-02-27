package com.cloudwing.checkstand.common.enums;

/**
 * 平台用户角色 枚举
 */
public enum PlatformRoleEnum {
    ADMIN("admin", "主账号"),
    ORDINARY("ordinary", "普通账号"),
    PLATFORMADMIN("platform_admin", "超级平台管理员"),
    PLATFORMORDINARY("platform_ordinary", "平台管理员")
    ;

    PlatformRoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

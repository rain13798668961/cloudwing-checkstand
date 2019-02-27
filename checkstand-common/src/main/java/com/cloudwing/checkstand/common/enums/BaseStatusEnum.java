package com.cloudwing.checkstand.common.enums;

/**
 * 基础返回对象字段 status状态码 枚举类
 */
public enum BaseStatusEnum {

    SUCCESS("10000", "操作成功"),
    FAILED("20000", "操作失败"),
    NOTLOGIN("30000", "用户未登录或登录已失效"),

    ParamError("20001", "请求参数错误！"),


    ;

    private BaseStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

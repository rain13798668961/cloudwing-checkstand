package com.cloudwing.checkstand.common.enums;

/**
 * 订单状态  枚举
 */
public enum PayStatusEnum {

    NOTPAY(0, "未支付"),
    SUCCESS(1, "支付成功"),
    FAIL(3, "支付失败"),
    UNKNOWN(-1000, "未知错误"),
    USERPAYING(2, "支付中"),
    REFUNDING(4, "退款中"),
    REFUND(5, "已退款"),
    REVOKING(6, "撤销中"),
    REVOKED(7, "已撤销"),
    CANCEL(-1, "已取消"),
    FINISHED(10, "已完结"),
    REFUNDFAIL(-5, "退款失败")


    ;
    /**
     * 状态码
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusDesc;

    private PayStatusEnum(Integer status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}

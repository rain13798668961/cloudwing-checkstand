package com.cloudwing.checkstand.trade.dto;

/**
 * 支付订单下单 数据对象
 */
public class OrderPlaceDto {

    /**
     * 下单现场 代码
     */
    private String orderOfficeSelect;

    /**
     * 下单商户 代码
     */
    private String orderMchSelect;

    /**
     * 订单支付类型
     */
    private String orderOpTypeSelect;

    /**
     * 商品主体
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 订单总金额
     */
    private Integer sum;

    /**
     * 备注
     */
    private String note;

    public OrderPlaceDto() {}

    public String getOrderOfficeSelect() {
        return orderOfficeSelect;
    }

    public void setOrderOfficeSelect(String orderOfficeSelect) {
        this.orderOfficeSelect = orderOfficeSelect;
    }

    public String getOrderMchSelect() {
        return orderMchSelect;
    }

    public void setOrderMchSelect(String orderMchSelect) {
        this.orderMchSelect = orderMchSelect;
    }

    public String getOrderOpTypeSelect() {
        return orderOpTypeSelect;
    }

    public void setOrderOpTypeSelect(String orderOpTypeSelect) {
        this.orderOpTypeSelect = orderOpTypeSelect;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

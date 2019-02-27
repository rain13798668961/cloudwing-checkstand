package com.cloudwing.checkstand.trade.dto;

import com.cloudwing.checkstand.common.dto.BaseQueryDTO;

/**
 * 订单查询 条件数据对象
 */
public class OrderQueryDto extends BaseQueryDTO {

    /**
     * 订单编号
     */
    public String orderNo;

    /**
     * 支付状态
     */
    private String status;

   public OrderQueryDto() {
       super();
   }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

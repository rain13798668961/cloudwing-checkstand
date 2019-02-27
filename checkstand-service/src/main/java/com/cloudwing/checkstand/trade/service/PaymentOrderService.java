package com.cloudwing.checkstand.trade.service;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.cloudwing.checkstand.trade.dto.OrderPlaceDto;
import com.cloudwing.checkstand.trade.dto.OrderQueryDto;
import com.cloudwing.checkstand.trade.entity.PaymentOrder;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageInfo;

public interface PaymentOrderService {

    /**
     * 获取支付订单列表
     * @param user
     * @param orderQueryDto
     * @return
     */
    BaseResult<PageInfo<PaymentOrder>> listByConditions(User user, OrderQueryDto orderQueryDto);

    /**
     * 下单（操作员创建支付订单）
     * @param user
     * @param orderPlaceDto
     * @return
     */
    BaseResult<Object> placeOrder(User user, OrderPlaceDto orderPlaceDto);


    /**
     * 提交支付
     */
    BaseResult<Object> submitOrder(User user, String orderNo, String authCode);

    /**
     * 支付结果通知回调
     * @param payResult
     * @return
     */
    BaseResult<Object> notifyOrder(PayResult payResult);
}

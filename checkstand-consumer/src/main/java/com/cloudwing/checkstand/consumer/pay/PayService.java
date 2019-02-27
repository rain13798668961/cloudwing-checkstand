package com.cloudwing.checkstand.consumer.pay;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.cloudwing.checkstand.consumer.pay.dto.PayQueryResult;
import com.cloudwing.checkstand.consumer.pay.dto.RefundResult;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.yunyitg.rpc.base.exception.RpcException;

/**
 * 支付服务-服务接口
 * @author 云翼RPC-java-代码自动生成
 *
 */
public interface PayService {

    /**
     * 支付提交
     * @param appCode 应用代码
     * @param merchantCode 云翼商户代码
     * @param termNo 应用订单流水号
     * @param subject 商品主体
     * @param body 商品描述
     * @param sum 支付总额
     * @param note 备注
     * @param authCode 付款码
     * @param notifyUrl 结果通知url
     * @return  支付返回结果对象
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    PayResult submit(String appCode, String merchantCode, String termNo, String subject, String body, Integer sum, String note, String authCode, String notifyUrl) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 退款
     * @param appCode 应用代码
     * @param merchantCode 商户代码
     * @param termNo 调用方订单号
     * @param outTradeNo 云翼支付订单号
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    RefundResult refund(String appCode, String merchantCode, String termNo, String outTradeNo) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 查询
     * @param appCode 应用代码
     * @param merchantCode 商户代码
     * @param termNo 调用方订单号
     * @param outTradeNo 云翼支付订单号
     * @return  支付查询返回结果对象
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    PayQueryResult orderQuery(String appCode, String merchantCode, String termNo, String outTradeNo) throws InterruptedException, ExecutionException, TimeoutException, RpcException;

    /**
     * 关闭服务
     */
    public void close();

    /**
     * 连接是否有效
     */
    public boolean isActive();
}

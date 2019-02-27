package com.cloudwing.checkstand.consumer.pay;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


import com.cloudwing.checkstand.consumer.pay.dto.PayQueryResult;
import com.cloudwing.checkstand.consumer.pay.dto.RefundResult;
import com.cloudwing.checkstand.consumer.pay.dto.PayResult;
import com.google.common.reflect.TypeToken;
import com.yunyitg.rpc.base.exception.RpcException;
import com.yunyitg.rpc.base.YunyiTextMsg;
import com.yunyitg.rpc.common.YunyiRpcClientFactory;
import com.yunyitg.rpc.consumer.YunyiRpcClient;
import org.springframework.stereotype.Service;

/**
 * 支付服务-服务接口-实现类
 * @author 云翼RPC-java-代码自动生成
 *
 */
@Service
public class PayServiceImpl implements PayService {
    

    private YunyiRpcClient yunyiRpcClient;
    private YunyiRpcClientFactory yunyiRpcClientFactory;

    public PayServiceImpl() {}

    public PayServiceImpl(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public void setYunyiRpcClientFactory(YunyiRpcClientFactory yunyiRpcClientFactory) {
        this.yunyiRpcClientFactory = yunyiRpcClientFactory;
    }

    public synchronized YunyiRpcClient getYunyiRpcClient() throws InterruptedException, ExecutionException, TimeoutException, RpcException {
        if(yunyiRpcClient == null){
            yunyiRpcClient = yunyiRpcClientFactory.createRpcClient();
        }
        return yunyiRpcClient;
    }

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
    @Override
    public PayResult submit(String appCode, String merchantCode, String termNo, String subject, String body, Integer sum, String note, String authCode, String notifyUrl) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<PayResult> result = getYunyiRpcClient().invoke(new TypeToken<YunyiTextMsg<PayResult>>(){}.getType(), "Pay.Pay.submit", appCode, merchantCode, termNo, subject, body, sum, note, authCode, notifyUrl);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 退款
     * @param appCode 应用代码
     * @param merchantCode 商户代码
     * @param termNo 调用方订单号
     * @param outTradeNo 云翼支付订单号
     * @return
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public RefundResult refund(String appCode, String merchantCode, String termNo, String outTradeNo) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<RefundResult> result = getYunyiRpcClient().invoke(new com.google.gson.reflect.TypeToken<YunyiTextMsg<RefundResult>>(){}.getType(), "Pay.Pay.refund", appCode, merchantCode, termNo, outTradeNo);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 查询
     * @param appCode 应用代码
     * @param merchantCode 商户代码
     * @param termNo 调用方订单号
     * @param outTradeNo 云翼支付订单号
     * @return  支付查询返回结果对象
     * @throws InterruptedException, ExecutionException, TimeoutException, RpcException
     */
    @Override
    public PayQueryResult orderQuery(String appCode, String merchantCode, String termNo, String outTradeNo) throws InterruptedException, ExecutionException, TimeoutException, RpcException{
        YunyiTextMsg<PayQueryResult> result = getYunyiRpcClient().invoke(new com.google.gson.reflect.TypeToken<YunyiTextMsg<PayQueryResult>>(){}.getType(), "Pay.Pay.orderQuery", appCode, merchantCode, termNo, outTradeNo);
        if (result.isSuccess()) {
            return result.getResponse();
        }else {
            throw new RpcException(result.getMsg());
        }
    }

    /**
     * 关闭服务
     */
    @Override
    public void close(){
        if(yunyiRpcClient != null){
            yunyiRpcClient.close();
        }
    }

    /**
     * 连接是否有效
     */
    @Override
    public boolean isActive(){
        return yunyiRpcClient.isActive();
    }
}

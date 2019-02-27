package com.cloudwing.checkstand.consumer.pay.dto;

import java.util.List;

/**
 * 支付返回结果对象
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class PayResult {

    /**
     * 应用订单流水号
     */
    private String termNo;

    /**
     * 支付订单流水号
     */
    private String outTradeNo;

    /**
     * 交易状态/结果
     */
    private String tradeStatus;

    /**
     * 交易结果描述
     */
    private String tradeMsg;

    /**
     * get应用订单流水号
     */
    public String getTermNo(){
        return this.termNo;
    }

    /**
     * set应用订单流水号
     */
    public void setTermNo(String termNo){
        this.termNo = termNo;
    }

    /**
     * get支付订单流水号
     */
    public String getOutTradeNo(){
        return this.outTradeNo;
    }

    /**
     * set支付订单流水号
     */
    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }

    /**
     * get交易状态/结果
     */
    public String getTradeStatus(){
        return this.tradeStatus;
    }

    /**
     * set交易状态/结果
     */
    public void setTradeStatus(String tradeStatus){
        this.tradeStatus = tradeStatus;
    }

    /**
     * get交易结果描述
     */
    public String getTradeMsg(){
        return this.tradeMsg;
    }

    /**
     * set交易结果描述
     */
    public void setTradeMsg(String tradeMsg){
        this.tradeMsg = tradeMsg;
    }

}

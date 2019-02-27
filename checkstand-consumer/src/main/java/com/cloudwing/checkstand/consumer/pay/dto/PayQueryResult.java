package com.cloudwing.checkstand.consumer.pay.dto;

/**
 * 支付查询返回结果对象
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class PayQueryResult {

	/**
	 * 云翼商户代码
	 */
	private String merchantCode;

	/**
	 * 应用内部订单号
	 */
	private String termNo;

	/**
	 * 云翼支付订单号
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
	 * 请求是否成功
	 */
	private Boolean returnCode;

	/**
	 * 请求的描述
	 */
	private String returnMsg;

	/**
	 * get云翼商户代码
	 */
	public String getMerchantCode(){
		return this.merchantCode;
	}

	/**
	 * set云翼商户代码
	 */
	public void setMerchantCode(String merchantCode){
		this.merchantCode = merchantCode;
	}

	/**
	 * get应用内部订单号
	 */
	public String getTermNo(){
		return this.termNo;
	}

	/**
	 * set应用内部订单号
	 */
	public void setTermNo(String termNo){
		this.termNo = termNo;
	}

	/**
	 * get云翼支付订单号
	 */
	public String getOutTradeNo(){
		return this.outTradeNo;
	}

	/**
	 * set云翼支付订单号
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

	/**
	 * get请求是否成功
	 */
	public Boolean getReturnCode(){
		return this.returnCode;
	}

	/**
	 * set请求是否成功
	 */
	public void setReturnCode(Boolean returnCode){
		this.returnCode = returnCode;
	}

	/**
	 * get请求的描述
	 */
	public String getReturnMsg(){
		return this.returnMsg;
	}

	/**
	 * set请求的描述
	 */
	public void setReturnMsg(String returnMsg){
		this.returnMsg = returnMsg;
	}

	@Override
	public String toString() {
		return "PayQueryResult{" +
				"merchantCode='" + merchantCode + '\'' +
				", termNo='" + termNo + '\'' +
				", outTradeNo='" + outTradeNo + '\'' +
				", tradeStatus='" + tradeStatus + '\'' +
				", tradeMsg='" + tradeMsg + '\'' +
				", returnCode=" + returnCode +
				", returnMsg='" + returnMsg + '\'' +
				'}';
	}
}

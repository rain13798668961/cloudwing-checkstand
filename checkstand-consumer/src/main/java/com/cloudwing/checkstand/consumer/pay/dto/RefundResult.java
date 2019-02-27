package com.cloudwing.checkstand.consumer.pay.dto;

/**
 * 
 * @author 云翼RPC-java-代码自动生成
 *
 */
public class RefundResult {

	/**
	 * 应用订单流水号
	 */
	private String termNo;

	/**
	 * 云翼支付订单号
	 */
	private String outTradeNo;

	/**
	 * 退款状态（结果）
	 */
	private String refundStatus;

	/**
	 * 请求是否成功
	 */
	private Boolean returnCode;

	/**
	 * 请求的描述
	 */
	private String returnMsg;

	/**
	 * 退款结果描述
	 */
	private String refundMsg;

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
	 * get退款状态（结果）
	 */
	public String getRefundStatus(){
		return this.refundStatus;
	}

	/**
	 * set退款状态（结果）
	 */
	public void setRefundStatus(String refundStatus){
		this.refundStatus = refundStatus;
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

	/**
	 * get退款结果描述
	 */
	public String getRefundMsg(){
		return this.refundMsg;
	}

	/**
	 * set退款结果描述
	 */
	public void setRefundMsg(String refundMsg){
		this.refundMsg = refundMsg;
	}

}

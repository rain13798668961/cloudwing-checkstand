package com.cloudwing.checkstand.office.dto;

/**
 * 现场与商户是否关联  视图对象
 */
public class OfficeMchSwitchDto {

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户代码
     */
    private String merchantCode;

    /**
     * 是否关联标志（Y-是，N-否）
     */
    private String isRelated;

    public OfficeMchSwitchDto() {}

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getIsRelated() {
        return isRelated;
    }

    public void setIsRelated(String isRelated) {
        this.isRelated = isRelated;
    }
}

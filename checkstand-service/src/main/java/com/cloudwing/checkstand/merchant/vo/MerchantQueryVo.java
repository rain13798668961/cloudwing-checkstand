package com.cloudwing.checkstand.merchant.vo;

import java.util.Date;

/**
 * Create by cjf on 2019/2/12.
 */
public class MerchantQueryVo {
    private Integer id;
    /**所属公司id*/
    private Integer companyId;
    /**所属公司名*/
    private String companyName;
    /**商户名称*/
    private String name;
    /**发票抬头*/
    private String invoiceTitle;
    /**云翼商户代码*/
    private String cwMerchantCode;
    /**备注*/
    private String note;
    /**数据创建者id*/
    private Integer createUserId;
    /**数据创建者name*/
    private String createUserName;
    /**数据更改者id*/
    private Integer updateUserId;
    /**数据更改者nama*/
    private String updateUserName;
    /**数据创建时间*/
    private Date createAt;
    /**数据更改时间*/
    private Date updateAt;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getCwMerchantCode() {
        return cwMerchantCode;
    }

    public void setCwMerchantCode(String cwMerchantCode) {
        this.cwMerchantCode = cwMerchantCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "MerchantQueryVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", name='" + name + '\'' +
                ", invoiceTitle='" + invoiceTitle + '\'' +
                ", cwMerchantCode='" + cwMerchantCode + '\'' +
                ", note='" + note + '\'' +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", updateUserId=" + updateUserId +
                ", updateUserName='" + updateUserName + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}

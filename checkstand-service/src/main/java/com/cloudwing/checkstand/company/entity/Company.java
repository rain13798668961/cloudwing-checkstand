package com.cloudwing.checkstand.company.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer platformCompanyId;

    private String companyCode;

    private String registerName;

    private String registerIdentityCode;

    private String registerTel;

    private String status;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPlatformCompanyId() {
        return platformCompanyId;
    }

    public void setPlatformCompanyId(Integer platformCompanyId) {
        this.platformCompanyId = platformCompanyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    public String getRegisterIdentityCode() {
        return registerIdentityCode;
    }

    public void setRegisterIdentityCode(String registerIdentityCode) {
        this.registerIdentityCode = registerIdentityCode == null ? null : registerIdentityCode.trim();
    }

    public String getRegisterTel() {
        return registerTel;
    }

    public void setRegisterTel(String registerTel) {
        this.registerTel = registerTel == null ? null : registerTel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}
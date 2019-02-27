package com.cloudwing.checkstand.user.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer companyId;

    private Integer platformUserId;

    private Integer platformCompanyId;

    private Integer roleId;

    private String name;

    private String account;

    private String password;

    private String phone;

    private String email;

    private String avatar;

    private String avatarType;

    private String status;

    private String note;

    private Date createAt;

    private Date updateAt;

    @Transient
    private Set<String> roles;

    /**
     * 云翼外贸圈平台sessionid
     */
    @Transient
    private String sessionid;


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

    public Integer getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(Integer platformUserId) {
        this.platformUserId = platformUserId;
    }

    public Integer getPlatformCompanyId() {
        return platformCompanyId;
    }

    public void setPlatformCompanyId(Integer platformCompanyId) {
        this.platformCompanyId = platformCompanyId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType == null ? null : avatarType.trim();
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", platformUserId=" + platformUserId +
                ", platformCompanyId=" + platformCompanyId +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", avatarType='" + avatarType + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", roles=" + roles +
                '}';
    }
}
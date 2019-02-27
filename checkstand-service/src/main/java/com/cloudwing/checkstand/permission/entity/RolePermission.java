package com.cloudwing.checkstand.permission.entity;

import javax.persistence.Id;

public class RolePermission {
    @Id
    private Integer rid;

    @Id
    private Integer pid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
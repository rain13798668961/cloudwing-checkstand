package com.cloudwing.checkstand.common.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基础查询 参数
 */
public class BaseQueryDTO extends PageQueryDTO {

    /**
     * 入库时间起始
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startAt;

    /**
     * 入库时间结束
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endAt;

    public BaseQueryDTO() {}

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}

package com.cloudwing.checkstand.common.dto;

import java.io.Serializable;

/**
 * 分页查询 参数
 */
public class PageQueryDTO implements Serializable {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 一页数据数量
     */
    private Integer limit;

    public PageQueryDTO() {}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

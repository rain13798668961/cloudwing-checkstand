package com.cloudwing.checkstand.office.dto;

import com.cloudwing.checkstand.common.dto.PageQueryDTO;

/**
 * 现场查询 条件数据对象
 */
public class OfficeQueryDto extends PageQueryDTO {

    /**
     * 现场名称
     */
    private String name;

    /**
     * 现场代码
     */
    private String code;

    public OfficeQueryDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

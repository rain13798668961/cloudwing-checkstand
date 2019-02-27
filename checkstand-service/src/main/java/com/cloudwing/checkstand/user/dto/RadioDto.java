package com.cloudwing.checkstand.user.dto;

/**
 * 办公现场下拉框数据对象
 */
public class RadioDto {

    /**
     * 现场代码
     */
    String code;

    /**
     * 现场名称
     */
    String name;

    public RadioDto() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

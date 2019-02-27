package com.cloudwing.checkstand.common.exception;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;

/**
 * 业务异常父类
 * Create by cjf on 2019/2/22.
 */
public class BaseBusinessException extends RuntimeException {
    private String code;

    // 给子类用的方法
    public BaseBusinessException(BaseStatusEnum baseStatusEnum) {
        this(baseStatusEnum.getMsg(), baseStatusEnum.getStatus());
    }

    public BaseBusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

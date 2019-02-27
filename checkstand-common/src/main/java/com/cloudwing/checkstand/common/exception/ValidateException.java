package com.cloudwing.checkstand.common.exception;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;

/**
 * Create by cjf on 2019/1/24.
 */
public class ValidateException extends BaseBusinessException {
    public ValidateException(BaseStatusEnum baseStatusEnum) {
        super(baseStatusEnum);
    }
}

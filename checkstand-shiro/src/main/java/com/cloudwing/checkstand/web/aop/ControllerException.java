package com.cloudwing.checkstand.web.aop;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.exception.BaseBusinessException;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.common.result.BaseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Create by cjf on 2019/2/22.
 */
@RestControllerAdvice
public class ControllerException {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理所有不可知的异常
     * @param e 异常
     * @return
     */
    @ExceptionHandler({Exception.class})    //申明捕获那个异常类
    public BaseResult globalExceptionHandler(Exception e) {
        this.log.error(e.getMessage(), e);
        return new BaseResultUtil().error(BaseStatusEnum.FAILED);
    }

    /**
     * 处理所有业务异常
     * @param e 异常
     * @return
     */
    @ExceptionHandler({BaseBusinessException.class})
    public BaseResult BaseBusinessExceptionHandler(BaseBusinessException e) {
        this.log.error(e.getMessage(), e);
        return new BaseResultUtil().error(e.getCode(), e.getMessage());
    }
}

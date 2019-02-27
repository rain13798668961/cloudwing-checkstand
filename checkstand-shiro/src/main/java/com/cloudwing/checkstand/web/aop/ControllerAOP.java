package com.cloudwing.checkstand.web.aop;

import com.cloudwing.checkstand.common.result.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;


@Component
@Aspect
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    /**
     * controller层切入点
     */
    @Pointcut("execution(com.cloudwing.checkstand.common.result.BaseResult com.cloudwing.checkstand.web.*.*(..))")
    public void exceptionAspect(){

    }

    @Around("exceptionAspect()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        BaseResult<?> result;

        try {
            result = (BaseResult<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private BaseResult<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        BaseResult<?> result = new BaseResult();
        // 已知异常
//        if (e instanceof CheckException) {
//        } else if (e instanceof UnloginException) {
//            result.setMsg("Unlogin");
//            result.setCode(StateInfo.NO_LOGIN);
//        } else {
//            logger.error(pjp.getSignature() + " error ", e);
//            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
//            YunyiLogApi yunyiLogApi = new YunyiLogImp();
//            //没有文件也要new个files[]过去
//            List<File> files = new ArrayList();
//            //调用PHP方的接口实现微信异常推送
//            yunyiLogApi.logInException(projectCode, projectSecret, e, files);
//
//        }
        result.setMsg(e.getMessage());
        result.setStatus("20000");

        return result;
    }
}

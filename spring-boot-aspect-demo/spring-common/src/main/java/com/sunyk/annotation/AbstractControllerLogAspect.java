/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.annotation;

import com.sunyk.enums.BizErrorCodeEnum;
import com.sunyk.exceptions.BizException;
import com.sunyk.facade.BizBaseResponse;
import com.sunyk.util.JsonBinder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 抽象类 日志切面
 *
 * @author sunyang
 * @date 2018/12/19 14:30
 */
public abstract class AbstractControllerLogAspect {

    private static final Logger log = LoggerFactory.getLogger(AbstractControllerLogAspect.class);

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public AbstractControllerLogAspect() {
    }

    /**
     * 定义个抽象的方法
     */
    public abstract void controllerLog();

    /**
     * 操作之前的逻辑处理（方法开始之前）
     * @param joinPoint
     * @param controllerLogAspectAnnotation
     * @throws Throwable
     */
    @Before("controllerLog() && @annotation(controllerLogAspectAnnotation)")
    public void doBefore(JoinPoint joinPoint, ControllerLogAspectAnnotation controllerLogAspectAnnotation) throws Throwable{
        if (controllerLogAspectAnnotation.isPrintPostData()){
            log.info(controllerLogAspectAnnotation.description() + "开始调用：" + "requestData = {}", joinPoint.getArgs());
        }

        if (controllerLogAspectAnnotation.isPrintSpendTime()){
            this.threadLocal.set(System.currentTimeMillis());
        }

    }

    /**
     * 操作之后在返回之前的逻辑处理（方法执行完后，就开始doafter）它通常用于释放资源和类似目的
     * @param joinPoint
     * @param controllerLogAspectAnnotation
     * @throws Throwable
     */
    @After("controllerLog() && @annotation(controllerLogAspectAnnotation)")
    public void doAfter(JoinPoint joinPoint, ControllerLogAspectAnnotation controllerLogAspectAnnotation) throws Throwable{
        log.info("doAfter:");
    }

    /**
     * 操作返回之后的逻辑处理(最后执行)
     * @param joinPoint
     * @param result
     * @param controllerLogAspectAnnotation
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "controllerLog() && @annotation(controllerLogAspectAnnotation)")
    public void doAfterReturning(JoinPoint joinPoint, Object result, ControllerLogAspectAnnotation controllerLogAspectAnnotation) throws Throwable{
        if (controllerLogAspectAnnotation.isPrintResultData()){
            log.info(controllerLogAspectAnnotation.description() + "结束调用：" + "result = {}", result);
        }

        if (controllerLogAspectAnnotation.isPrintSpendTime()){
            log.info(controllerLogAspectAnnotation.description() + "总用时：" + (System.currentTimeMillis() - this.threadLocal.get().longValue()) + "毫秒");
            threadLocal.remove();
        }
    }

    /**
     * 定义抛错之后通知处理（doafter之后，最后执行之前）
     *
     *
     * @param joinPoint
     * @param controllerLogAspectAnnotation
     * @param exception
     * @throws Throwable
     */
    @AfterThrowing(value = "controllerLog() && @annotation(controllerLogAspectAnnotation)", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,ControllerLogAspectAnnotation controllerLogAspectAnnotation, Throwable exception) throws Throwable{
        BizErrorCodeEnum errorCode = BizErrorCodeEnum.SYSTEM_ERROR;
        String errorMessage = "";
        if (controllerLogAspectAnnotation.isPrintThrowing()){
            log.error(controllerLogAspectAnnotation.description() + "操作异常：requestData = {}", joinPoint.getArgs(), exception);
            if (isNoticeException(exception)){
                BizException bizException = (BizException) exception;
                errorCode = bizException.getErrorCode();
                errorMessage = bizException.getErrorMessage();
            }else{
                errorMessage = exception.getMessage();
            }
        }
        doResponse(errorCode, errorMessage);
    }

    private void doResponse(BizErrorCodeEnum errorCode, String errorMessage) {
        //从RequestContextHolder中获得对应HttpServletResponse
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;text/json;charset=UTF-8");
            response.setStatus(200);
            response.setHeader("Access-Control-Allow-Origin", "*");
            out = response.getOutputStream();
            out.write(JsonBinder.buildNonDefaultBinder().toJson(new BizBaseResponse(errorCode, errorMessage)).getBytes("UTF-8"));
        } catch (IOException var2) {
            var2.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }
    }

    private boolean isNoticeException(Throwable exception) {
        return exception instanceof BizException;
    }

}


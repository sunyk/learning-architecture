/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author sunyang
 * @date 2018/12/19 18:39
 */
/*@Aspect
@Component
public class ServiceMonitor {

    //暂时不用
    @AfterReturning("execution(* com.sunyk.*.controller.UseWebFluxController.useLog())")
    public void logServiceAccess(JoinPoint joinPoint){
        System.out.println("Completed:" + joinPoint);
    }
}*/


/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.annotation;

import java.lang.annotation.*;

/**
 * 日志切面注解
 *
 * @author sunyang
 * @date 2018/12/19 14:23
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
public @interface ControllerLogAspectAnnotation {
    //描述
    String description() default "";

    //打印请求参数
    boolean isPrintPostData() default true;

    //打印响应结果
    boolean isPrintResultData() default true;

    //打印异常
    boolean isPrintThrowing() default true;

    //打印消耗时间
    boolean isPrintSpendTime() default false;

    //默认值
    String value() default "";

}


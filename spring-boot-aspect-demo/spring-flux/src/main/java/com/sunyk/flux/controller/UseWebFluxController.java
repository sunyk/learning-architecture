/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.flux.controller;

import com.sunyk.annotation.ControllerLogAspectAnnotation;
import com.sunyk.enums.BizErrorCodeEnum;
import com.sunyk.exceptions.BizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sunyang
 * @date 2018/12/19 14:40
 */
@RestController
public class UseWebFluxController {

    @GetMapping("/")
    @ControllerLogAspectAnnotation(description = "start...", isPrintPostData = true)
    public String useLog(){

        System.out.println(100/0);

        System.out.println("use log....");

        return null;
    }
}


/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.client.web.controller;

import com.sunyk.spring.cloud.feign.api.domain.Person;
import com.sunyk.spring.cloud.feign.client.service.MangenPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sunyang
 * @date 2018/12/27 17:03
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired(required = false)
    private MangenPersonService mangenPersonService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "/getPerson")
    public String getPerson() {
        Person person = mangenPersonService.findPerson();
        String info = person.toString();
        return info;
    }
}


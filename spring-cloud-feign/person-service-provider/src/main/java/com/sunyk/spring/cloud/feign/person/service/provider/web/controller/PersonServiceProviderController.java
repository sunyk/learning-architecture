/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.person.service.provider.web.controller;

import com.sunyk.spring.cloud.feign.api.domain.Person;
import com.sunyk.spring.cloud.feign.api.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务提供方
 * {@link PersonService} 提供者控制器
 *
 * @author sunyang
 * @date 2018/12/27 15:39
 */
@RestController
public class PersonServiceProviderController {

    private final Map<Long, Person> persons = new ConcurrentHashMap<>();

    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person){
        System.out.println("save is here....");
        return persons.put(person.getId(), person) == null;
    }


    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAll(){
        System.out.println("persons is here....");
        return persons.values();
    }

}


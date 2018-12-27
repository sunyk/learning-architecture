/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.client.web.controller;

import com.sunyk.spring.cloud.feign.api.domain.Person;
import com.sunyk.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * client 作为服务的消费者
 * {@link PersonClientController} 实现 {@link PersonService}
 *
 * @author sunyang
 * @date 2018/12/27 15:20
 */
@RestController
public class PersonClientController implements PersonService{

    private final PersonService personService;

    @Autowired
    public PersonClientController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean save(Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        System.out.println("client: " + System.currentTimeMillis());
        return personService.findAll();
    }
}


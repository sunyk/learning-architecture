/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.client.service.impl;

import com.sunyk.spring.cloud.feign.api.domain.Person;
import com.sunyk.spring.cloud.feign.api.service.PersonService;
import com.sunyk.spring.cloud.feign.client.service.MangenPersonService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * @author sunyang
 * @date 2018/12/27 17:07
 */
@Service
public class MangenPersonServiceImpl implements MangenPersonService {

    private final PersonService personService;

    public MangenPersonServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Person findPerson() {
        Collection<Person> persons = personService.findAll();
        Person person = persons.stream().findAny().get();
        return person;
    }
}


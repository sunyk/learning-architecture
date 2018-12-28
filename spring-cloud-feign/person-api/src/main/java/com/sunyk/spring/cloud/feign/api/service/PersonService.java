/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.api.service;

import com.sunyk.spring.cloud.feign.api.domain.Person;
import com.sunyk.spring.cloud.feign.api.hystrix.PersonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;

/**
 * Feign 声明式接口
 * @FeignClient(value = "person-service") 服务提供方应用的名称
 * @author sunyang
 * @date 2018/12/27 15:07
 */
@FeignClient(value = "person-service", fallback = PersonServiceFallback.class)
public interface PersonService {

    /**
     * 保存用户
     * @param person {@link Person}
     * @return 成功返回，<code>true</code>
     */
    @PostMapping(value = "/person/save")
    boolean save(@RequestBody Person person);

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll();

}


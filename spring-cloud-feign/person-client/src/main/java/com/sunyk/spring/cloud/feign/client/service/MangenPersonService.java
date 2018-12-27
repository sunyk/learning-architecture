/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.sunyk.spring.cloud.feign.client.service;

import com.sunyk.spring.cloud.feign.api.domain.Person;

import java.util.Collection;

/**
 * @author sunyang
 * @date 2018/12/27 17:06
 */
public interface MangenPersonService {

    Person findPerson();
}


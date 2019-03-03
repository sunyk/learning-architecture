package com.sunyk.demo.service.impl;

import com.sunyk.annotation.Service;
import com.sunyk.demo.service.DemoService;

/**
 * Create by sunyang on 2019/3/3 19:05
 * For me:One handred lines of code every day,make myself stronger.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String select(String userName) {
        return "This is a " + userName;
    }
}

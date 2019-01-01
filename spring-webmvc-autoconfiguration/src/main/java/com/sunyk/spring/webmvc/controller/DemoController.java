package com.sunyk.spring.webmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by sunyang on 2018/12/31 17:36
 * For me:One handred lines of code every day,make myself stronger.
 */
@RestController
public class DemoController {

    @GetMapping("/")
    public String index(){
        return "Hello world";
    }
}

package com.sunyk.demo.controller;

import com.sunyk.annotation.Autowired;
import com.sunyk.annotation.Controller;
import com.sunyk.annotation.RequestMapping;
import com.sunyk.annotation.RequestParam;
import com.sunyk.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by sunyang on 2019/3/3 17:57
 * For me:One handred lines of code every day,make myself stronger.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/index.json")
    public void index(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name){
        String userName = demoService.select(name);
        System.err.println(userName);
    }
}

package com.sunyk.spring.webmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * 自动化配置dispatchservlet
 * Create by sunyang on 2018/12/31 17:36
 * For me:One handred lines of code every day,make myself stronger.
 */
public class AutoConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebMvcConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}

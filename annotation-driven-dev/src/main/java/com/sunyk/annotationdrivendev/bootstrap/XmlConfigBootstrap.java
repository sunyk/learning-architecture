package com.sunyk.annotationdrivendev.bootstrap;

import com.sunyk.annotationdrivendev.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XML 引导程序
 *
 * Create by sunyang on 2018/12/31 15:11
 * For me:One handred lines of code every day,make myself stronger.
 */
public class XmlConfigBootstrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("classpath:/META-INF/spring/context.xml");
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println("xml properties userName : " + user.getUserName());

    }
}

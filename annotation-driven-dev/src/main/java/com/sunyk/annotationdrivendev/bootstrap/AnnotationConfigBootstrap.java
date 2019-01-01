package com.sunyk.annotationdrivendev.bootstrap;

import com.sunyk.annotationdrivendev.config.UserConfigration;
import com.sunyk.annotationdrivendev.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解引导程序
 *
 * Create by sunyang on 2018/12/31 15:21
 * For me:One handred lines of code every day,make myself stronger.
 */
public class AnnotationConfigBootstrap {

    public static void main(String[] args) {
        //构建一个上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册带注解的bean
        applicationContext.register(UserConfigration.class);
        applicationContext.refresh();

        User user =  applicationContext.getBean("user", User.class);
        System.out.println("annotation diver userName:" + user.getUserName());

    }
}

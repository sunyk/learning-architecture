package com.sunyk.annotationdrivendev.config;

import com.sunyk.annotationdrivendev.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by sunyang on 2018/12/31 15:16
 * For me:One handred lines of code every day,make myself stronger.
 */
@Configuration
public class UserConfigration {

    @Bean(name = "user")
    public User user(){
        User user = new User();
        user.setUserName("sunyk");
        return user;
    }
}

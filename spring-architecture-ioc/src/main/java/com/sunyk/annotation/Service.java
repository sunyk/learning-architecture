package com.sunyk.annotation;

import java.lang.annotation.*;

/**
 * Create by sunyang on 2019/3/3 18:24
 * For me:One handred lines of code every day,make myself stronger.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}

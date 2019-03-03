package com.sunyk.annotation;

import java.lang.annotation.*;

/**
 * Create by sunyang on 2019/3/3 18:26
 * For me:One handred lines of code every day,make myself stronger.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}

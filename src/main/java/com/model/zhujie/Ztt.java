package com.model.zhujie;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
import java.util.Map;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ztt {
    String variables() default "";

    String wfType() default "";

    String callBack() default "";



}

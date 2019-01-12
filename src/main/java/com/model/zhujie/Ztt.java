package com.model.zhujie;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyRegisterOther.class})
public @interface Ztt {
    String value() default "";
}

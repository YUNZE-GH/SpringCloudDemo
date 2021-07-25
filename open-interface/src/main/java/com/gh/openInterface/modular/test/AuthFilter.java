package com.gh.openInterface.modular.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/24 16:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthFilter {
    String value() default "";
}

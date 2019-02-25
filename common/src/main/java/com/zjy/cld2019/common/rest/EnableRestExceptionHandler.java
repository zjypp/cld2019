package com.zjy.cld2019.common.rest;


import com.zjy.cld2019.common.rest.advice.RestExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在启动类上增加该注解，实现统一的Rest异常返回处理；
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(RestExceptionHandler.class)
public @interface EnableRestExceptionHandler {
    //test
}

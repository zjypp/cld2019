package com.zjy.cld2019.common.rest.advice;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.rest.RestResponseBuilder;
import com.zjy.cld2019.common.rest.error.ServiceError;
import com.zjy.cld2019.common.rest.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: Jason Zhang
 * @Date: 2019/1/25 3:50 PM
 * @Version: 1.0
 * @Des：统一的rest异常控制
 * 需要在springboot项目的启动类增加注解@EnableRestExceptionHandler，才能生效
 */
@RestControllerAdvice
@EnableWebMvc
public class RestExceptionHandler {
    Logger logger=  LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler()
    @ResponseBody
    RestResponse handleException(Exception e){

        RestResponseBuilder restResponseBuilder  = new RestResponseBuilder();

        String exMsg = e.getMessage();
        logger.info(exMsg);
        logger.error(exMsg);
        e.printStackTrace();

        RestResponse response = new RestResponse();
        if (e instanceof RestException) {
            response.setCode(((RestException) e).getCode());
            response.setMsg(((RestException) e).getMsg());
            response.setInfo(((RestException) e).getInfo());
        } else if (e instanceof IllegalArgumentException) {
            response = restResponseBuilder.fail(ServiceError.SY0105);
        }else{
            response = restResponseBuilder.fail(ServiceError.INTERNAL_ERROR);
        }
        return response;
    }
}

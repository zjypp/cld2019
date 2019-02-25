package com.zjy.cld2019.common.rest;

import com.zjy.cld2019.common.rest.*;
import com.zjy.cld2019.common.rest.error.ServiceError;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: Jason Zhang
 * @Date: 2019/1/28 11:12 AM
 * @Version: 1.0
 * @Des：BaseResponse构造器
 */
public class RestResponseBuilder<E> {





    public RestResponse success(E response) {
        RestResponse<E> result = new RestResponse<E>();
        result.setCode("1");
        result.setT(response);
        return  result;
    }
    public  RestResponse fail(ServiceError errorCode) {
        RestResponse<E> result = new RestResponse<E>();
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMessage());
        return  result;
    }

    public  RestResponse fail(E response, ServiceError serviceError) {
        RestResponse<E> result = new RestResponse<E>();
        result.setCode(serviceError.getCode());
        result.setMsg(serviceError.getMessage());
        result.setT(response);
        return  result;
    }
}

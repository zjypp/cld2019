package com.zjy.cld2019.common.rest;


import com.zjy.cld2019.common.rest.error.ServiceError;

/*
cloud服务返回的数据基础类型结构
 */
public class RestResponse<T> {

    private String code;//返回结果编码，后期可能需要专门维护；默认1成功
    private String msg;//返回给用户的提示信息
    private String info;//返回给系统的维护信息
    private String status;
    private T t;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

}

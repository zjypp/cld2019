package com.zjy.cld2019.common.rest.exception;


import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * rest统一异常返回处理；
 */
public class RestException extends RuntimeException {

    private String code;
    private String msg;//给用户的提示
    private String info;//系统错误信息

    public RestException(ServiceError error)
    {
        super(error.getMessage());
        this.code = error.getCode();
        this.msg = error.getMessage();
        this.info= error.getInfo();
    }
    public RestException(String code, String msg, String info){
        super(msg);
        this.code=code;
        this.msg =msg;
        this.info = info;
    }

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




}

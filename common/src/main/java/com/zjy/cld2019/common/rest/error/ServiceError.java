package com.zjy.cld2019.common.rest.error;



import java.io.Serializable;

/**
 * 编码定义规则：
 * 默认编码长度为6位
 * 开头两位是系统编码
 * 中间二位编码表示编码分类 例如
 * 00表示系统异常的编码
 * 01表示参数校验错误类的编码等等
 * 02表示业务状态不合法的编码
 * 03表示网络相关的异常的编码等
 * 后两位再根据前两位的分类定义具体的编码
 * 例如定义一个输入金额不能为空的异常码 0101
 * 定义一个 报文反序列化异常的编码0001
 *
 */
public class ServiceError implements Serializable {

    private String code;
    private String message;


    private String info;
    public static final ServiceError INTERNAL_ERROR = new ServiceError("SY9999","未知错误");
    public static final ServiceError INTERNAL_OFFLINE_ERROR = new ServiceError("SY9998","服务不可用");

    /**
     * 参数校验类异常 SY01XX
     */
    public static final ServiceError SY0101 = new ServiceError("SY0101","参数不能为空");

    public static final ServiceError SY0102 = new ServiceError("SY0102","参数超过最大长度限制");

    public static final ServiceError SY0103 = new ServiceError("SY0103","参数必须为数字");

    public static final ServiceError SY0104 = new ServiceError("SY0104","正则表达式校验不通过");

    public static final ServiceError SY0105 = new ServiceError("SY0105","参数校验不通过");

    public static final ServiceError SY0106 = new ServiceError("SY0106", "参数长度不满足要求");

    public ServiceError(){}

    public ServiceError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceError(String code, String message,String info) {
        this.code = code;
        this.message = message;
        this.info=info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

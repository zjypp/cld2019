package com.zjy.cld2019.common.utils;

import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 11:26 AM
 * @Version: 1.0
 * @Des：应用业务的密码的工具类
 */
public class PwdUtil {

    /**
     * 注册的密码生成
     * @param password
     * @return
     */
    public static String generateRegPwd(String password){
        return MD5Utils.encryptPassword(password,"Abc123");
    }
}

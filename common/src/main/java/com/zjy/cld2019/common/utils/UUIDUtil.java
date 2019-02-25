package com.zjy.cld2019.common.utils;

import java.util.UUID;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 11:15 AM
 * @Version: 1.0
 * @Des：
 */
public class UUIDUtil {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

}

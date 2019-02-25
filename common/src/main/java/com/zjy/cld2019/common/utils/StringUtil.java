package com.zjy.cld2019.common.utils;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 11:35 AM
 * @Version: 1.0
 * @Des：
 */
public class StringUtil {
    /**
     * 判断是否为空字符串最优代码
     * @param str
     * @return 如果为空，则返回true
     */
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param str 如果不为空，则返回true
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}

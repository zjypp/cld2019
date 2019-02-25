package com.zjy.cld2019.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 11:37 AM
 * @Version: 1.0
 * @Des：
 */
public class PhoneUtil {

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            //MToast.showToast("手机号应为11位数");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();

            if (!isMatch) {
                //MToast.showToast("请填入正确的手机号");
            }
            return isMatch;
        }
    }


}

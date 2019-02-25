package com.zjy.cld2019.userservice.error;

import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/25 4:02 PM
 * @Version: 1.0
 * @Des：
 */
public class UserServiceError extends ServiceError {
    private UserServiceError(String code, String message) {
        super(code,message);
    }

    public static final UserServiceError US020001 = new UserServiceError("US020001","该手机号用户已存在");
    public static final UserServiceError US020002 = new UserServiceError("US020002","角色已存在");
    public static final UserServiceError US020003 = new UserServiceError("US020003", "用户不存在");

    public static final UserServiceError US020004 = new UserServiceError("US020004", "用户密码错误");
    public static final UserServiceError US020005 = new UserServiceError("US020005", "用户已禁用");

}

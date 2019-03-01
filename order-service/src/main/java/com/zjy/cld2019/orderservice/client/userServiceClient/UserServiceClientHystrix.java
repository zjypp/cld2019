package com.zjy.cld2019.orderservice.client.userServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.orderservice.client.userServiceClient.model.User;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 10:35 AM
 * @Version: 1.0
 * @Des：
 */
public class UserServiceClientHystrix implements UserServiceClient {
    @Override
    public RestResponse<User> getUser(String id) {
        return null;
    }

    @Override
    public RestResponse<User> getUserByUserId(String userId) {
        return null;
    }

    @Override
    public RestResponse<User> getUserByPhone(String phone) {
        return null;
    }
}

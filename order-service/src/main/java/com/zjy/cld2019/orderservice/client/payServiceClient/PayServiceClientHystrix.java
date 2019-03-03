package com.zjy.cld2019.orderservice.client.payServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.orderservice.client.payServiceClient.model.UserAccount;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 11:32 AM
 * @Version: 1.0
 * @Des：
 */
@Component
public class PayServiceClientHystrix implements PayServiceClient {
    @Override
    public RestResponse<UserAccount> getUserAccount(String userId) {
        return null;
    }

    @Override
    public RestResponse<Boolean> usePay(String userId, String payPassword, double amount) {
        return null;
    }
}

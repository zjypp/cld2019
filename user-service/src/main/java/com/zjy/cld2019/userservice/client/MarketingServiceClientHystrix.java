package com.zjy.cld2019.userservice.client;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.userservice.client.model.MarketingCoupon;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 6:53 PM
 * @Version: 1.0
 * @Des：MarketingService服务熔断。不可用情况下的异常处理。
 */
@Component
public class MarketingServiceClientHystrix implements  MarketingServiceClient {
    @Override
    public RestResponse<List<MarketingCoupon>> getUserCoupons(String userId) {
        return null;
    }

    @Override
    public RestResponse<Boolean> addUserCoupon(String userId, Integer type, Integer money) {
        return null;
    }

    @Override
    public RestResponse<Boolean> useCoupon(String userId, String couponId) {
        return null;
    }
}

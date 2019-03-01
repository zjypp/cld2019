package com.zjy.cld2019.orderservice.client.marketingServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.orderservice.client.marketingServiceClient.model.MarketingCoupon;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 6:36 PM
 * @Version: 1.0
 * @Des：
 */
public class MarketingServiceClientHystrix implements MarketingServiceClient {
    @Override
    public RestResponse<MarketingCoupon> getMarketingCoupon(String couponId) {
        return null;
    }

    @Override
    public RestResponse<List<MarketingCoupon>> getUserCouponList(String userId) {
        return null;
    }

    @Override
    public RestResponse<Boolean> useCoupon(String userId, String couponId) {
        return null;
    }
}

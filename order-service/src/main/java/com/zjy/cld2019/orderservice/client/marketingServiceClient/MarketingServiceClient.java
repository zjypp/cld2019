package com.zjy.cld2019.orderservice.client.marketingServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.orderservice.client.marketingServiceClient.model.MarketingCoupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 6:33 PM
 * @Version: 1.0
 * @Des：
 */
@FeignClient(value = "marketing-service",fallback = MarketingServiceClientHystrix.class)
public interface MarketingServiceClient {

    @RequestMapping(value = "/marketing/getusercoupon",method = RequestMethod.POST)
    RestResponse<MarketingCoupon> getMarketingCoupon(@RequestParam("couponId") String couponId);

    @RequestMapping(value = "/marketing/getusercoupons",method = RequestMethod.POST)
    RestResponse<List<MarketingCoupon>> getUserCouponList(@RequestParam("userId") String userId);

    @RequestMapping(value = "/marketing/usecoupon",method = RequestMethod.POST)
    RestResponse<Boolean> useCoupon(@RequestParam("userId") String userId, @RequestParam("couponId") String couponId);
}

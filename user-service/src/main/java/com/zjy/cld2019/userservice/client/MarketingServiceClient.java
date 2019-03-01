package com.zjy.cld2019.userservice.client;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.userservice.client.model.MarketingCoupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 5:00 PM
 * @Version: 1.0
 * @Des：marketingService的调用类
 */
@FeignClient(value = "marketing-service",fallback = MarketingServiceClientHystrix.class)
public interface MarketingServiceClient {

    /**
     * 查询某个用户下的优惠券
     * @param userId
     * @return
     */
    @RequestMapping(value = "/marketing/getusercoupons",method = RequestMethod.POST)
    RestResponse<List<MarketingCoupon>> getUserCoupons(@RequestParam("userId") String userId);

    /**
     * 给某个用户发送优惠券
     * @param userId
     * @param type
     * @param money
     * @return
     */
    @RequestMapping(value = "/marketing/addusercoupon",method = RequestMethod.POST)
    RestResponse<Boolean> addUserCoupon(@RequestParam("userId") String userId,@RequestParam("type") Integer type ,@RequestParam("money") Integer money);


    /**
     * 某个用户使用优惠券
     * @param userId
     * @param couponId
     * @return
     */
    @RequestMapping(value = "/marketing/usecoupon",method = RequestMethod.POST)
    RestResponse<Boolean> useCoupon(@RequestParam("userId") String userId,@RequestParam("couponId") String couponId);

}

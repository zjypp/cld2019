package com.zjy.cld2019.marketingservice.controller;

import com.zjy.cld2019.common.rest.controller.BaseController;
import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.marketingservice.error.MarketingServiceError;
import com.zjy.cld2019.marketingservice.model.MarketingCoupon;
import com.zjy.cld2019.marketingservice.service.MarketingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/27 4:19 PM
 * @Version: 1.0
 * @Des：用户营销-优惠券相关Api
 */
@RestController
@Api(value = "营销服务相关的api接口")
@RequestMapping(value = "/marketing")
public class MarketingController extends BaseController {

    @Autowired
    MarketingService marketingService;


    @RequestMapping(value = "/getusercoupon",method = RequestMethod.POST)
    public RestResponse<MarketingCoupon> getMarketingCoupon(String couponId){

        if(StringUtil.isEmpty(couponId)){
            return restResponseBuilder.fail(MarketingServiceError.SY0105);
        }

        MarketingCoupon coupon = marketingService.getUserCoupon(couponId);
        if(coupon!=null){
            return restResponseBuilder.success(coupon);
        }else{
            return restResponseBuilder.fail(MarketingServiceError.MK030003);
        }

    }

    @RequestMapping(value = "/getusercoupons",method = RequestMethod.POST)
    public RestResponse<List<MarketingCoupon>> getUserCouponList(String userId){

        List<MarketingCoupon> list = marketingService.getUserCouponList(userId);
        if(list !=null && list.size()>0){
            return restResponseBuilder.success(list);
        }
        else{
            return restResponseBuilder.fail(MarketingServiceError.MK030004);
        }
    }

    @RequestMapping(value = "/addusercoupon",method = RequestMethod.POST)
    public RestResponse<Boolean> addUserCoupon(String userId,Integer type ,Integer money){
        MarketingCoupon coupon = new MarketingCoupon();
        coupon.setUserId(userId);
        coupon.setType(type);
        coupon.setMoney(money);
        Boolean result =marketingService.addUserCoupon(coupon);

        return  restResponseBuilder.success(result);

    }

    @RequestMapping(value = "/usecoupon",method = RequestMethod.POST)
    public RestResponse<Boolean> useCoupon(String userId,String couponId){

        MarketingCoupon coupon = new MarketingCoupon();
        coupon.setUserId(userId);
        coupon.setCouponId(couponId);
       Boolean result = marketingService.useCoupon(coupon);
       return restResponseBuilder.success(result);

    }
}

package com.zjy.cld2019.marketingservice.service;

import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.common.utils.UUIDUtil;
import com.zjy.cld2019.marketingservice.dao.MarketingMapper;
import com.zjy.cld2019.marketingservice.model.MarketingCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/27 4:30 PM
 * @Version: 1.0
 * @Des：
 */
@Service
public class MarketingService {

    @Autowired
    MarketingMapper marketingMapper;

    public MarketingCoupon getUserCoupon(String couponId){
        if(StringUtil.isNotEmpty(couponId)){
            return marketingMapper.getUserCoupon(couponId);
        }else{
            return null;
        }

    }

    public List<MarketingCoupon> getUserCouponList(String userId){
        if(StringUtil.isNotEmpty(userId)) {
            return marketingMapper.getUserCoupons(userId);
        }else{
            return null;
        }

    }

    public boolean addUserCoupon(MarketingCoupon coupon){
        coupon.setCouponId(UUIDUtil.getUUID());
        int count = marketingMapper.addUserCoupons(coupon);
        if(count >0){
            return true;
        }
        else{
            return  false;
        }
    }

    public boolean useCoupon(MarketingCoupon coupon){
        int count = marketingMapper.useCoupon(coupon);
        return  count==1?true:false;
    }
}

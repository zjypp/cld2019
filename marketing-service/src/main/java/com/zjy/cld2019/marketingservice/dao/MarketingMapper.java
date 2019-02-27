package com.zjy.cld2019.marketingservice.dao;

import com.zjy.cld2019.marketingservice.model.MarketingCoupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/27 4:34 PM
 * @Version: 1.0
 * @Des：
 */
@Mapper
public interface MarketingMapper {

    @Select("select * from marketing_coupon where coupon_id=#{couponId} limit 1 ")
    MarketingCoupon getUserCoupon(String couponId);

    @Select("select * from marketing_coupon where user_id=#{userId}")
    List<MarketingCoupon> getUserCoupons(String userId);

    @Insert("insert marketing_coupon(coupon_id,user_id,type,money) values(#{couponId},#{userId},#{type},#{money}) ")
    int addUserCoupons(MarketingCoupon coupon);

    @Update("update marketing_coupon set `status`=1 where user_id= #{userId} and coupon_id=#{couponId} ")
    int useCoupon(MarketingCoupon coupon);

}

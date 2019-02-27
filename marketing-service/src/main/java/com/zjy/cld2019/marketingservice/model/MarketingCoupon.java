package com.zjy.cld2019.marketingservice.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/27 4:23 PM
 * @Version: 1.0
 * @Des：
 */
public class MarketingCoupon extends BaseModel {

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    private String couponId;
    private String userId;
    private Integer type;
    private Integer status;
    private double money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

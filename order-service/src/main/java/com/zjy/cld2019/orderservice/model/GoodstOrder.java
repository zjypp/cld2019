package com.zjy.cld2019.orderservice.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 8:34 AM
 * @Version: 1.0
 * @Des 商品订单
 */
public class GoodstOrder extends BaseModel {

    private String userId;
    private String skuId;
    private int amount;
    private double price;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

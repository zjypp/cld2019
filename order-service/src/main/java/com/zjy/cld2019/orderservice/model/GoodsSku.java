package com.zjy.cld2019.orderservice.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 9:15 AM
 * @Version: 1.0
 * @Des：
 */
public class GoodsSku  extends BaseModel {
    private String skuId;
    private double price;
    private int availableStock;
    private int freezeStock;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getFreezeStock() {
        return freezeStock;
    }

    public void setFreezeStock(int freezeStock) {
        this.freezeStock = freezeStock;
    }
}

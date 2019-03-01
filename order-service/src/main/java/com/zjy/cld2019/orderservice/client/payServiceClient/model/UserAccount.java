package com.zjy.cld2019.orderservice.client.payServiceClient.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:12 PM
 * @Version: 1.0
 * @Des：
 */
public class UserAccount extends BaseModel {

    private String userId;
    private double freezeAmount;
    private double availableAmount;
    private String payPassword;
    private int status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(double freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

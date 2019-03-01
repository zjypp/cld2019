package com.zjy.cld2019.payservice.model;

import com.zjy.cld2019.common.model.BaseModel;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:14 PM
 * @Version: 1.0
 * @Des：
 */
public class PaymentRecord extends BaseModel {

    private String userId;
    private double amount;
    private int status;
    private int type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package com.zjy.cld2019.orderservice.error;

import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 9:08 AM
 * @Version: 1.0
 * @Des：
 */
public class OrderServiceError extends ServiceError {

    private OrderServiceError(String code, String message) {
        super(code,message);
    }

    public static final OrderServiceError OR020001 = new OrderServiceError("OR020001","该商品库存订单不足");
    public static final OrderServiceError OR020002 = new OrderServiceError("OR020002","订单生成失败");
    public static final OrderServiceError OR020003 = new OrderServiceError("OR020003","该商品sku不存在");
    public static final OrderServiceError OR020004 = new OrderServiceError("OR020004","该用户没有开通支付账户");
    public static final OrderServiceError OR020005 = new OrderServiceError("OR020005","该用户账户资金不足");

}

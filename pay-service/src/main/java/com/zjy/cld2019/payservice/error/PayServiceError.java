package com.zjy.cld2019.payservice.error;

import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:06 PM
 * @Version: 1.0
 * @Des：
 */
public class PayServiceError extends ServiceError {
    private PayServiceError(String code, String message) {
        super(code,message);
    }

    public static final PayServiceError PY040001 = new PayServiceError("PY040001","该用户账户不存在，无法查询账户资金");
    public static final PayServiceError PY040002 = new PayServiceError("PY040002","账户资金不足，无法提现");
    public static final PayServiceError PY040003 = new PayServiceError("PY040003","该用户账户不存在，无法操作充值提现");
    public static final PayServiceError PY040004 = new PayServiceError("PY040004","支付密码错误");
    public static final PayServiceError PY040005 = new PayServiceError("PY040005","支付失败");
}

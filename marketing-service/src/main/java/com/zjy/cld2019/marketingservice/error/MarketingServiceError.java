package com.zjy.cld2019.marketingservice.error;

import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * @Author: Jason Zhang
 * @Date: 2019/2/27 4:22 PM
 * @Version: 1.0
 * @Des：
 */
public class MarketingServiceError extends ServiceError {
    private MarketingServiceError(String code, String message) {
        super(code,message);
    }

    public static final MarketingServiceError MK030001 = new MarketingServiceError("MK030001","该用户不存在");
    public static final MarketingServiceError MK030002 = new MarketingServiceError("MK030002","该优惠券不可用");
    public static final MarketingServiceError MK030003 = new MarketingServiceError("MK030003","该优惠券不存在");
    public static final MarketingServiceError MK030004 = new MarketingServiceError("MK030004","该用户下没有优惠券");
}

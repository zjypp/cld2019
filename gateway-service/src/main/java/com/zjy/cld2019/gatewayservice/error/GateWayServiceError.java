package com.zjy.cld2019.gatewayservice.error;

import com.zjy.cld2019.common.rest.error.ServiceError;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/4 4:59 PM
 * @Version: 1.0
 * @Des：
 */
public class GateWayServiceError extends ServiceError {
    private GateWayServiceError(String code, String message) {
        super(code,message);
    }

    public static final GateWayServiceError GW050001 = new GateWayServiceError("GW050001","Auth验证无效，网关拦截");

}

package com.zjy.cld2019.orderservice.client.payServiceClient;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.orderservice.client.payServiceClient.model.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 11:17 AM
 * @Version: 1.0
 * @Des：
 */
@FeignClient(value = "pay-service")
public interface PayServiceClient {
    /**
     * 查询支付账户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/pay/getuseraccount",method =RequestMethod.POST)
    RestResponse<UserAccount> getUserAccount(@RequestParam("userId") String userId);

    /**
     * 支付
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/pay/usePay",method = RequestMethod.POST)
    RestResponse<Boolean> usePay(@RequestParam("userId") String userId, @RequestParam("payPassword") String payPassword, @RequestParam("amount") double amount);

}

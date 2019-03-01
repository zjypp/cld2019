package com.zjy.cld2019.payservice.controller;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.rest.controller.BaseController;
import com.zjy.cld2019.common.rest.error.ServiceError;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.payservice.error.PayServiceError;
import com.zjy.cld2019.payservice.model.UserAccount;
import com.zjy.cld2019.payservice.service.PayService;
import io.swagger.annotations.Api;
import org.bouncycastle.cert.ocsp.Req;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Jason Zhang
 * @Date: 2019/2/28 7:04 PM
 * @Version: 1.0
 * @Des：
 */
@RequestMapping(value = "/pay")
@Api(value = "pay service")
@RestController
public class PayContoller extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PayService payService;

    /**
     * 开通支付账户
     * @param userId
     * @param payPassword
     * @return
     */
    @RequestMapping(value = "/openuseraccount",method = RequestMethod.POST)
    public RestResponse<Boolean> openUserAccount(String userId,String payPassword){
        if(StringUtil.isEmpty(userId)){
            return restResponseBuilder.fail(ServiceError.SY0105);
        }

        if(StringUtil.isEmpty(payPassword) || payPassword.length()<6){
            return restResponseBuilder.fail(ServiceError.SY0105);
        }

        boolean result = payService.openUserAccount(userId,payPassword);
        return  restResponseBuilder.success(result);
    }

    /**
     * 查询支付账户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getuseraccount",method =RequestMethod.POST)
    public RestResponse<UserAccount> getUserAccount(String userId){

        UserAccount userAccount = payService.getUserAccount(userId);
        return restResponseBuilder.success(userAccount);
    }

    /**
     * 充值
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/recharge",method = RequestMethod.POST)
    public RestResponse<Boolean> recharge(String userId,double amount){
        if(StringUtil.isEmpty(userId)){
            return restResponseBuilder.fail(ServiceError.SY0105);
        }
        if(payService.getUserAccount(userId)==null){
            return restResponseBuilder.fail(PayServiceError.PY040003);
        }

        boolean result = payService.recharge(userId,amount);
        return restResponseBuilder.success(result);
    }

    /**
     * 支付
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/usePay",method = RequestMethod.POST)
    public RestResponse<Boolean> recharge(String userId,String payPassword,double amount){
        if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(payPassword) || payPassword.length()<6){
            return restResponseBuilder.fail(ServiceError.SY0105);
        }
        if(payService.getUserAccount(userId)==null){
            return restResponseBuilder.fail(PayServiceError.PY040003);
        }

        int code = payService.useMoney(userId,amount,payPassword);
        if(code == -1){
            return restResponseBuilder.fail(PayServiceError.PY040004);
        }else if(code == 0){
            return restResponseBuilder.fail(PayServiceError.PY040005);
        }else if(code == 1){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.fail(ServiceError.SY0101);
        }
    }
}

package com.zjy.cld2019.orderservice.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.cld2019.common.rest.RestResponse;

import com.zjy.cld2019.common.rest.controller.BaseController;
import com.zjy.cld2019.common.rest.error.ServiceError;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.orderservice.client.marketingServiceClient.MarketingServiceClient;
import com.zjy.cld2019.orderservice.client.marketingServiceClient.model.MarketingCoupon;
import com.zjy.cld2019.orderservice.client.payServiceClient.PayServiceClient;
import com.zjy.cld2019.orderservice.client.payServiceClient.model.UserAccount;
import com.zjy.cld2019.orderservice.client.userServiceClient.UserServiceClient;
import com.zjy.cld2019.orderservice.client.userServiceClient.model.User;
import com.zjy.cld2019.orderservice.error.OrderServiceError;
import com.zjy.cld2019.orderservice.model.GoodsSku;
import com.zjy.cld2019.orderservice.model.GoodstOrder;
import com.zjy.cld2019.orderservice.service.OrderService;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Jason Zhang
 * @Date: 2019/3/1 9:12 AM
 * @Version: 1.0
 * @Des：
 */
@RestController
@Api(value = "Order servcie")
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;
    @Autowired
    PayServiceClient payServiceClient;
    @Autowired
    UserServiceClient userServiceClient;
    @Autowired
    MarketingServiceClient marketingServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${server.port}")
    String port;

    @GetMapping("/test")
    public String test(){
        return "test;"+port;
    }

    /**
     * 下订单
     * @param userId
     * @param skuId 商品id
     * @param amount 订单数量
     * @param couponId 优惠券id
     * @Des
     * 验证userid的用户是否存在，
     * 验证userid的支付账户是否开通以及有足够金额，
     * 验证库存是足够
     * 验证优惠券是否有效可用，如果可用，使用该优惠券
     * @return
     */
    @RequestMapping(value = "/addorder",method = RequestMethod.POST)
    public RestResponse<Boolean> addOrder(String userId,String skuId,Integer amount,String couponId,String payPassword){

        RestResponse<User> userRestResponse = userServiceClient.getUserByUserId(userId);
        //验证用户是否存在
        if(userRestResponse ==null || !userRestResponse.getCode().equals("1")){
            //把user服务异常代码信息返回
            ServiceError serviceError = new ServiceError();
            serviceError.setCode(userRestResponse.getCode());
            serviceError.setMessage(userRestResponse.getMsg());
            return restResponseBuilder.fail(serviceError);

        }

        //验证库存
        GoodsSku goodsSku =orderService.getGoodsSku(skuId);
        if(goodsSku == null){
            return restResponseBuilder.fail(OrderServiceError.OR020003);
        }
        else if(goodsSku !=null && goodsSku.getAvailableStock() < amount){//库存不足
            return restResponseBuilder.fail(OrderServiceError.OR020001);
        }
        //验证用户支付账户是否有效以及是否有足够资金
        //计算该skuid 的price*订单amount的总金额
        RestResponse<UserAccount> userAccountRestResponse= payServiceClient.getUserAccount(userId);
        if(userAccountRestResponse == null || !userAccountRestResponse.getCode().equals("1") || userAccountRestResponse.getT() ==null){

            return restResponseBuilder.fail(OrderServiceError.OR020004);
        }
        //订单总金额
        double orderPrice = goodsSku.getPrice() * amount;
        if(userAccountRestResponse.getT().getAvailableAmount() < orderPrice ){
            return restResponseBuilder.fail(OrderServiceError.OR020005);
        }

        //该账户进行支付，金额orderPrice
        RestResponse<Boolean> booleanPayRestResponse = payServiceClient.usePay(userId,payPassword,orderPrice);

        //验证支付密码及账户余额是否足够
        if(booleanPayRestResponse == null || !booleanPayRestResponse.getCode().equals("1")){
            ServiceError serviceError = new ServiceError();
            serviceError.setCode(booleanPayRestResponse.getCode());
            serviceError.setMessage(booleanPayRestResponse.getMsg());
            return restResponseBuilder.fail(serviceError);
        }

        //如果使用了优惠券，验证并使用
        if(StringUtil.isNotEmpty(couponId)){
            RestResponse<MarketingCoupon> marketingCouponRestResponse = marketingServiceClient.getMarketingCoupon(couponId);
            if(marketingCouponRestResponse !=null && marketingCouponRestResponse.getCode().equals("1") && marketingCouponRestResponse.getT() !=null){
                MarketingCoupon marketingCoupon = marketingCouponRestResponse.getT();
                //如果优惠券有限，并且属于userID，使用
                if(marketingCoupon.getStatus()==0 && marketingCoupon.getUserId().equals(userId)){
                    marketingServiceClient.useCoupon(userId,couponId);
                }
            }

        }

        GoodstOrder goodstOrder = new GoodstOrder();
        goodstOrder.setUserId(userId);
        goodstOrder.setAmount(amount);
        goodstOrder.setPrice(orderPrice);
        goodstOrder.setSkuId(skuId);
        int count = orderService.addOrder(goodstOrder);
        logger.info(JSON.toJSONString(userAccountRestResponse));
        return  count == 1?restResponseBuilder.success(true):restResponseBuilder.success(false);


    }


    @RequestMapping(value = "/decreasesku",method = RequestMethod.POST)
    public RestResponse<Boolean> decreaseSku(String skuId,Integer amount){
        Boolean result = orderService.decreaseGoodsSku(skuId,amount);
        if(result){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.fail(OrderServiceError.OR020001);
        }
    }


    @RequestMapping(value = "/decreaseskuexception",method = RequestMethod.POST)
    public RestResponse<Boolean> decreaseSkuException(String skuId,Integer amount){
        Boolean result = orderService.decreaseSkuMockWithExceptionTry(skuId,amount);
        if(result){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.fail(OrderServiceError.OR020001);
        }
    }
}

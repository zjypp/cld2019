package com.zjy.cld2019.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.rest.controller.BaseController;
import com.zjy.cld2019.common.utils.PhoneUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.userservice.client.MarketingServiceClient;
import com.zjy.cld2019.userservice.client.model.MarketingCoupon;
import com.zjy.cld2019.userservice.error.UserServiceError;
import com.zjy.cld2019.userservice.model.User;
import com.zjy.cld2019.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api("UserServiceApi")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    MarketingServiceClient marketingServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public RestResponse<Boolean> test(){
        logger.trace("日志输出trace");
        logger.debug("日志输出debug");
        logger.info("日志输出info");
        logger.warn("日志输出warn");
        logger.error("日志输出error");
        RestResponse<List<MarketingCoupon>> coupons = marketingServiceClient.getUserCoupons("aaa");

        RestResponse<Boolean> result = marketingServiceClient.addUserCoupon("aaa",0,100);

        if(result!=null && result.getCode().equals("1") && result.getT()==true){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.success(false);
        }

        //return "test";
    }



    @ApiOperation(value="get one user info", notes="")
    @RequestMapping(value = "/getuser",method = RequestMethod.POST)
    public RestResponse<User> getUser(String id){

        User u = userService.getUserById(Integer.parseInt(id));
        if(u!=null){
            return restResponseBuilder.success(u);
        }else{
            return restResponseBuilder.fail(UserServiceError.SY0101);
        }
    }
    @RequestMapping(value = "/getuserbyuserid",method = RequestMethod.POST)
    public RestResponse<User> getUserByUserId(String userId){

        User u = userService.getUserByUserid(userId);
        if(u!=null){
            return restResponseBuilder.success(u);
        }else{
            return restResponseBuilder.fail(UserServiceError.US020003);
        }
    }
    @ApiOperation(value="get one user info by phone", notes="")
    @RequestMapping(value = "/getuserbyphone",method = RequestMethod.POST)
    public RestResponse<User> getUserByPhone(String phone){

        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }


        User u = userService.getUserByPhone(phone);
        if(u!=null){
            return restResponseBuilder.success(u);
        }else{
            return restResponseBuilder.fail(UserServiceError.SY0101);
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public RestResponse<User> registerUser(String phone,String username,String realName,String password,Integer age){


        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }

        User user = new User();
        user.setPhoneNum(phone);
        user.setUserName(username);
        user.setRealName(realName);
        user.setUserPwd(password);
        user.setAge(age);

        User result = userService.registerUser(user);
        if(result !=null){
            //注册成功的用户发送优惠券
            marketingServiceClient.addUserCoupon(result.getUserId(),1,100);
            return restResponseBuilder.success(user);
        }
        else{
            return restResponseBuilder.fail(UserServiceError.US020001);
        }
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public RestResponse<Boolean> login(String phone,String password){

        //the phone number is error
        if(!PhoneUtil.isPhone(phone)){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }
        // the password is valiate
        if(StringUtil.isEmpty(password) && password.length() <6){
            return restResponseBuilder.fail(UserServiceError.SY0105);
        }

        boolean result =userService.loginUser(phone,password);
        if(result){
            return restResponseBuilder.success(true);
        }else{
            return restResponseBuilder.fail(UserServiceError.US020004);
        }

    }
}

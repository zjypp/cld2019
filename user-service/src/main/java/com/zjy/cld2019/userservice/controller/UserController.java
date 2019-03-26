package com.zjy.cld2019.userservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.zjy.cld2019.common.rest.RestResponse;
import com.zjy.cld2019.common.rest.controller.BaseController;
import com.zjy.cld2019.common.utils.PhoneUtil;
import com.zjy.cld2019.common.utils.StringUtil;
import com.zjy.cld2019.userservice.client.MarketingServiceClient;
import com.zjy.cld2019.userservice.error.UserServiceError;
import com.zjy.cld2019.userservice.model.User;
import com.zjy.cld2019.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api("UserServiceApi")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    MarketingServiceClient marketingServiceClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 未加锁的情况下，出现了重复读取aaa某个时刻的值。导致在aaa原值100的情况下，即使经过150的循环递减，也未能使aaa《=0
     * 加了redission的锁的情况下，读取aaa的值没有重复，递减至0
     * @return
     */
    @GetMapping("/redis")
    public String redisTest(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int n=0;n<=150;n++){
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        RLock rLock = redissonClient.getLock("redission_lock");
                        rLock.lock(100,TimeUnit.MILLISECONDS);
                        int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());
                        System.out.println("****----" +Thread.currentThread().getName()+"--" + stock);
                        if(stock>0){
                            redisTemplate.opsForValue().set("aaa",(stock-1)+"");
                        }
                        rLock.unlock();
                    }
                    catch (Exception e){
                        System.out.println("*****"+e.getMessage());
                    }
                }
            });
        }

        return "t";
    }

    @GetMapping("/v1")
    public String testV1(){
        return "v1";
    }
    @GetMapping("/v2")
    public String testV2(){
        return "v2";
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(String test) throws Exception {
        logger.trace("日志输出trace");
        logger.debug("日志输出debug");

        return hello(test);

    }
    @HystrixCommand(fallbackMethod ="helloHystrix" )
    public String hello(String test) throws Exception {
        if(test.equals("test")){
            logger.info("****** throw new exceptio ");
            throw new Exception();
        }
        else{
            return test;
        }
    }

    public String helloHystrix(){
        logger.info("****** hello hystrix");
        return "this is hello method Hystrix";
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

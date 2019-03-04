package com.zjy.cld2019.gatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/4 4:06 PM
 * @Version: 1.0
 * @Des：用于user-service服务熔点情况下
 */
@RestController
public class UserServiceFallbackController {

    @GetMapping("/user/fallback")
    public String userServiceFallback(){
        return " user servcie is unable to access";
    }
}

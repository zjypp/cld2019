package com.zjy.cld2019.gatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * consul 健康检查专用
 */
@RestController
public class HealthController {
    @GetMapping("/actuator/health")
    public String health(){
        return "SUCCESS";
    }

    @GetMapping("/consul/health")
    public String health2(){
        return "SUCCESS";
    }

}

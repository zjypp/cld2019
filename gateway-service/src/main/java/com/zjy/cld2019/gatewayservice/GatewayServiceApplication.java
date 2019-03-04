package com.zjy.cld2019.gatewayservice;

import com.zjy.cld2019.gatewayservice.filters.UserGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


    /**
     * 通过代码的方式，注入gatewayFilter
     * 其中必须添加f->f.stripPrefix(1)
     * 这样转发userapi的前缀才不会带入到user-service中，如果userapi也传入到user-service中
     * 则路径是不被user-service识别的
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(r ->
                r.path("/userapi/**")
                        .filters(f->f.stripPrefix(1))
                        .uri("lb://user-service/")
                        .filters(new UserGatewayFilter())
                        .id("user_service"))
                .build();
    }

}

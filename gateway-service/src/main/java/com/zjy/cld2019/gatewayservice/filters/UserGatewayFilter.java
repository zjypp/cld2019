package com.zjy.cld2019.gatewayservice.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/4 10:34 PM
 * @Version: 1.0
 * @Des：
 */
public class UserGatewayFilter implements GatewayFilter,Ordered {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String key = exchange.getRequest().getQueryParams().getFirst("key");
        logger.info("get key from user-service"+key);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}

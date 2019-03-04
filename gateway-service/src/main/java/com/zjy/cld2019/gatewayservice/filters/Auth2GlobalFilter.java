package com.zjy.cld2019.gatewayservice.filters;

import com.alibaba.fastjson.JSON;
import com.zjy.cld2019.gatewayservice.error.GateWayServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/4 4:53 PM
 * @Version: 1.0
 * @Des：网关拦截，返回信息
 */
@Component
public class Auth2GlobalFilter implements GlobalFilter,Ordered {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("***begin authGlobalFiler 2222 ");
        List<String > key2 = exchange.getRequest().getQueryParams().get("key2");
        //如果没有key或者key！=abc。返回
        if(key2 == null || !key2.get(0).equals("111")){
          ServerHttpResponse serverHttpResponse =  exchange.getResponse();
           String t = JSON.toJSONString(GateWayServiceError.GW050001);

            byte[] bits = t.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(bits);
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
          return  serverHttpResponse.writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

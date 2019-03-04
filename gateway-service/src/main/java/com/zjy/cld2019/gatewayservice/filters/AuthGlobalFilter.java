package com.zjy.cld2019.gatewayservice.filters;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * @Author: Jason Zhang
 * @Date: 2019/3/4 4:28 PM
 * @Version: 1.0
 * @Des：全局过滤器，不需要注册或者配置
 * 验证失败，跳转到新页面
 */
@Component
public class AuthGlobalFilter implements GlobalFilter,Ordered {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("***begin authGlobalFiler ");
        List<String > key = exchange.getRequest().getQueryParams().get("key");
        //如果没有key或者key！=abc。返回
        if(key == null || !key.get(0).equals("abc")){
            String url = "http://localhost:9902/consul/health";
            ServerHttpResponse response = exchange.getResponse();
            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().set(HttpHeaders.LOCATION, url);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    /**
     * 数字越小级别越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

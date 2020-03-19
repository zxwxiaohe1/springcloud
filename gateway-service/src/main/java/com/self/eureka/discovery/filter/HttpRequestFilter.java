package com.self.eureka.discovery.filter;

import com.self.cloud.common.utils.ConstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xiaohe
 * @description:
 * @date 2020/3/18 15:34
 */
@Slf4j
@Component
@Order(0)
public class HttpRequestFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest host = request.mutate().header(ConstantUtils.HTTP_X_FORWARDED_FOR, request.getRemoteAddress().getAddress().getHostAddress()).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build).then(Mono.fromRunnable(()->{
            log.info(" 后置 : " +exchange.getResponse().getStatusCode() + "\t"+ exchange.getRequest().getURI().toString());
        }));
    }

}

package com.bithumb.tide.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncFilter extends AbstractGatewayFilterFactory<AsyncFilter.Config> {

    public AsyncFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            request.mutate().headers(httpHeaders ->
                    httpHeaders.add("async-request-path", request.getPath().subPath(2).value())
            );
            log.info("async-request-path - {}", request.getPath().subPath(2).value());

            return chain.filter(exchange);
        }));
    }

    public static class Config {
    }
}

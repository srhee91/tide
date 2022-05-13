package com.bithumb.tide.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            Map<String, Object> requestLog = new LinkedHashMap<>();
            requestLog.put("localDateTime", LocalDateTime.now());
            requestLog.put("httpMethod", request.getMethodValue());
            requestLog.put("requestUri", request.getURI());
//            requestLog.put("requestHeader", request.getHeaders());
            log.info("Request - {}", requestLog);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Map<String, Object> responseLog = new LinkedHashMap<>();
                responseLog.put("localDateTime", LocalDateTime.now());
                responseLog.put("httpStatusCode", response.getStatusCode());
                log.info("Response: {}", responseLog);
            }));
        }));
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}

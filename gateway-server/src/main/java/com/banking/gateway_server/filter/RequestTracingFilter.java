package com.banking.gateway_server.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Order(1)
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestTracingFilter implements GlobalFilter {

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String correlationId = filterUtility.getCorrelationId(headers);
        if (correlationId != null) {
            log.debug("Banking URL ID: {}", correlationId);
        } else {
            correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            log.debug("Banking URL Generate ID: {}", correlationId);
        }
        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

}

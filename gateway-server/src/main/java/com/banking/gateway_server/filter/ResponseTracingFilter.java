package com.banking.gateway_server.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Order(2)
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ResponseTracingFilter {

    private final FilterUtility filterUtility;

    @Bean
    public GlobalFilter postFilter(){

//        return new GlobalFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                return null;
//            }
//        };

        return  (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders headers = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(headers);
                log.debug("Correlation : {}", correlationId);
                if (correlationId != null) {
                    exchange.getResponse().getHeaders().set(FilterUtility.CORRELATION_ID, correlationId);
                }
            }));
        };

    }
}

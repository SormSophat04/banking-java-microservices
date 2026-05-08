package com.banking.gateway_server.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "banking-ucl-id";

    public String getCorrelationId(HttpHeaders headers) {
        List<String> requestHeaderList = headers.get(CORRELATION_ID);
        if (requestHeaderList != null && !requestHeaderList.isEmpty()) {
            return requestHeaderList.get(0);
        }
        return null;
    }

    public ServerWebExchange setServerWebExchange(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate()
                .request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setServerWebExchange(exchange, CORRELATION_ID, correlationId);
    }
}

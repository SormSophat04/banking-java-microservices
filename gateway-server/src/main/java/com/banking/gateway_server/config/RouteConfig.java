package com.banking.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class RouteConfig {

    @Bean
    public RouteLocator localRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        p -> p.path("/banking/accounts/**")
                                .filters(f -> f
                                        .rewritePath("/banking/accounts/(?<segment>.*)","/${segment}"))
                                .uri("lb://ACCOUNT-SERVICE")
                ).build();
    }
}

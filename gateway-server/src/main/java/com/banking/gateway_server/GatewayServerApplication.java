package com.banking.gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator localRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						p -> p.path("/banking/accounts/**")
								.filters(f -> f
										.rewritePath("/banking/accounts/(?<segment>.*)","/${segment}")
										.addResponseHeader("Account-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNT-SERVICE")
				).route(
						p -> p.path("/banking/cards/**")
								.filters(f -> f
										.rewritePath("/banking/cards/(?<segment>.*)","/${segment}"))
								.uri("lb://CARD-SERVICE")
				).route(
						p -> p.path("/banking/loans/**")
								.filters(f -> f
										.rewritePath("/banking/loans/(?<segment>.*)","/${segment}"))
								.uri("lb://LOAN-SERVICE")
				).build();
	}
}

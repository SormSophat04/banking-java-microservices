package com.banking.gateway_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final KeyClockJwtConverter keyClockJwtConverter;

    public SecurityConfig(KeyClockJwtConverter keyClockJwtConverter) {
        this.keyClockJwtConverter = keyClockJwtConverter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/banking/accounts/**").hasRole("ACCOUNT")
                .pathMatchers("/banking/cards/**").hasRole("CARD")
                .pathMatchers("/banking/loans/**").authenticated()
        ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(keyClockJwtConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}

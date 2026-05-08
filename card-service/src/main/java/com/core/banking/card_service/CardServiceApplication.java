package com.core.banking.card_service;

import com.core.banking.card_service.config.CardServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
@EnableConfigurationProperties(CardServiceConfig.class)
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

}

package com.core.banking.card_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "card-service")
public class CardServiceConfig {
    public String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;
}

package com.core.banking.account_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Map;

@RefreshScope
@ConfigurationProperties(prefix = "account-service")
@Data
public class AccountServiceConfig {
    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;
}

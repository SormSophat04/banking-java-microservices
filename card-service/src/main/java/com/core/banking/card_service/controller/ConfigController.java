package com.core.banking.card_service.controller;

import com.core.banking.card_service.config.CardServiceConfig;
import com.core.banking.card_service.property.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/config")
public class ConfigController {

    private final CardServiceConfig cardServiceConfig;

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardServiceConfig.getMsg(), cardServiceConfig.getBuildVersion(),
                cardServiceConfig.getMailDetails(), cardServiceConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}

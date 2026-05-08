package com.core.banking.account_service.function;

import com.core.banking.account_service.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountFunction {

    @Bean
    Consumer<Long> updateCustomerCommunication(CustomerService customerService) {
        return customerId -> {
            customerService.updateCommunicationSent(customerId);
            log.info("Customer communication sent to customer with id {}", customerId);
        };
    }
}

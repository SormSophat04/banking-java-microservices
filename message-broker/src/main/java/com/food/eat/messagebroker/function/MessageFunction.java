package com.food.eat.messagebroker.function;

import com.food.eat.messagebroker.dto.CustomerMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class MessageFunction {

    @Bean
    Function<CustomerMessageDTO, CustomerMessageDTO> email(){
        return customerMessageDTO -> {
            log.info("Log sending email {}",customerMessageDTO );
            return customerMessageDTO;
        };
    }

    @Bean
    Function<CustomerMessageDTO, Long> sms(){
        return customerMessageDTO ->  {
            log.info("Log sending sms  {}",customerMessageDTO );
            return customerMessageDTO.getCustomerId();
        };
    }
}

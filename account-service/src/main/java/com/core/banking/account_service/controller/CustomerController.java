package com.core.banking.account_service.controller;

import com.core.banking.account_service.dto.*;
import com.core.banking.account_service.entity.Customer;
import com.core.banking.account_service.mapper.CustomerMapper;
import com.core.banking.account_service.services.CustomerService;
import com.core.banking.account_service.services.client.CardFeignClient;
import com.core.banking.account_service.services.client.LoanFeignClient;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {
    final CustomerService customerService;
    final CustomerMapper customerMapper;

    final CardFeignClient cardFeignClient;
    final LoanFeignClient loanFeignClient;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO dto) {
        Customer customer = customerMapper.toCustomer(dto);
        customer = customerService.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

//    @CircuitBreaker(name = "getCustomerDetailSpp", fallbackMethod = "getCustomerDetailFallback")
    @Retry(name = "retryCustomerDetail", fallbackMethod = "getCustomerDetailFallback")
    @GetMapping("/detail/{customerId}")
    public ResponseEntity<CustomerDetailDTO> getCustomerDetail(
            @RequestHeader("banking-ucl-id")  String correlationId,
            @PathVariable Long customerId
    ){
        log.debug("CorrelationId {}", correlationId);

        CustomerDetailDTO dto = new CustomerDetailDTO();
        Customer customerById = customerService.getCustomerById(customerId);
        CustomerDTO customerDTO = customerMapper.toCustomerDTO(customerById);

        List<CardResponseDTO> cardInfo = cardFeignClient.getCardInfo(correlationId ,customerId);
        List<LoanResponseDTO> loanInfo = loanFeignClient.getLoanInfo(correlationId, customerId);

        dto.setCustomerDTO(customerDTO);
        dto.setCardResponseDTOS(cardInfo);
        dto.setLoanResponseDTOS(loanInfo);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/limiter")
    @RateLimiter(name = "refreshLimiter", fallbackMethod = "refresh")
    public String refresh(){
        return "Welcome to Microservices";
    }
}

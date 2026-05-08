package com.core.banking.account_service.services.client;

import com.core.banking.account_service.dto.LoanResponseDTO;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "loan-service")
public interface LoanFeignClient {

    @GetMapping("/api/loans/{customerId}")
    List<LoanResponseDTO> getLoanInfo(
            @RequestHeader("banking-ucl-id")  String correlationId,
            @PathVariable Long customerId
    );
}

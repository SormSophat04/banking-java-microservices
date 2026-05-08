package com.core.banking.account_service.services.client;

import com.core.banking.account_service.dto.CardResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "card-service")
public interface CardFeignClient {

    @GetMapping("/api/cards/{customerId}")
    List<CardResponseDTO> getCardInfo(
            @RequestHeader("banking-ucl-id")  String correlationId,
            @PathVariable Long customerId
    );
}

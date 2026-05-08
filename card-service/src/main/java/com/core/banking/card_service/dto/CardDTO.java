package com.core.banking.card_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardDTO {
    private String cardId;
    private Long customerId;
    private String cardNumber;
    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;
    private LocalDate createDate;
}

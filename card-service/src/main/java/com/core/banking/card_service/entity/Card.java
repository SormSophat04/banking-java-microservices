package com.core.banking.card_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "cards")
public class Card {

    @Id
    private String cardId;
    private Long customerId;
    private String cardNumber;
    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;
    private LocalDate createDate;

}
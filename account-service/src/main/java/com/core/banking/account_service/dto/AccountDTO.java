package com.core.banking.account_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDTO {
    private Long customerId;
    private String accountType;
    private String brandAddress;
    private LocalDate createDate;
}

package com.core.banking.account_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate createDate;
}

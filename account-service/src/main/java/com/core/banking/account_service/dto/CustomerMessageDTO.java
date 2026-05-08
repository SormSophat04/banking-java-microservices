package com.core.banking.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMessageDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
}

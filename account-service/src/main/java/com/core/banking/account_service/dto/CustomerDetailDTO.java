package com.core.banking.account_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailDTO {
    private CustomerDTO customerDTO;
    private List<CardResponseDTO> cardResponseDTOS;
    private List<LoanResponseDTO> loanResponseDTOS;
}

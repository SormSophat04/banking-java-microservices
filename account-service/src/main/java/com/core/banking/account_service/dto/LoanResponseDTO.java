package com.core.banking.account_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanResponseDTO {
    private String loanId;
    private Long customerId;
    private String loanType;
    private BigDecimal amountPaid;
    private BigDecimal loanAmount;
    private BigDecimal outStandingAmount;
    private LocalDate startDate;
    private LocalDate createDate;
}

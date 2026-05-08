package com.core.banking.laon_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "loans")
public class Loan {
    @Id
    private String loanId;
    private Long customerId;
    private String loanType;
    private BigDecimal amountPaid;
    private BigDecimal loanAmount;
    private BigDecimal outStandingAmount;
    private LocalDate startDate;
    private LocalDate createDate;
}

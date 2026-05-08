package com.core.banking.laon_service.mapper;

import com.core.banking.laon_service.dto.LoanDTO;
import com.core.banking.laon_service.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public Loan toLoan(LoanDTO dto) {
        Loan loan = new Loan();
        loan.setLoanId(dto.getLoanId());
        loan.setCustomerId(dto.getCustomerId());
        loan.setLoanType(dto.getLoanType());
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setAmountPaid(dto.getAmountPaid());
        loan.setOutStandingAmount(dto.getOutStandingAmount());
        loan.setStartDate(dto.getStartDate());
        loan.setCreateDate(dto.getCreateDate());
        return loan;
    }
}

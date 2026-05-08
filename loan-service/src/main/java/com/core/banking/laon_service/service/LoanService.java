package com.core.banking.laon_service.service;

import com.core.banking.laon_service.entity.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(Loan loan);
    List<Loan> getLoans();
    List<Loan> getByCustomerId(Long customerId);

}

package com.core.banking.laon_service.impl;

import com.core.banking.laon_service.entity.Loan;
import com.core.banking.laon_service.repository.LoanRepository;
import com.core.banking.laon_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getByCustomerId(Long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

}

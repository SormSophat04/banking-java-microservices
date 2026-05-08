package com.core.banking.laon_service.repository;

import com.core.banking.laon_service.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanRepository extends MongoRepository<Loan, String> {
    List<Loan> findByCustomerId(Long customerId);
}

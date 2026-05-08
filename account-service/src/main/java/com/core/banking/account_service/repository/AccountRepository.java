package com.core.banking.account_service.repository;

import com.core.banking.account_service.entity.Account;
import com.core.banking.account_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Customer customer);
}

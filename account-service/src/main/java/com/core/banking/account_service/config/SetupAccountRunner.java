package com.core.banking.account_service.config;

import com.core.banking.account_service.entity.Account;
import com.core.banking.account_service.entity.Customer;
import com.core.banking.account_service.repository.AccountRepository;
import com.core.banking.account_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SetupAccountRunner implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Customer> existingCustomer = customerRepository.findByEmail("ronaldo@gmail.com");
        Customer customer;
        if (existingCustomer.isEmpty()) {
            customer = new Customer();
            customer.setEmail("ronaldo@gmail.com");
            customer.setName("Ronaldo");
            customer.setPhoneNumber("123456789");
            customer.setCreateDate(LocalDate.now());
            customer = customerRepository.save(customer);
            log.info("Customer Created");
        } else {
            customer = existingCustomer.get();
        }

        Optional<Account> existingAccount = accountRepository.findByCustomerId(customer);
        if (existingAccount.isEmpty()) {
            Account account = new Account();
            account.setCustomerId(customer);
            account.setAccountType("Loan");
            account.setBrandAddress("London");
            account.setCreateDate(LocalDate.now());
            accountRepository.save(account);
            log.info("Account Created");
        }
    }
}

package com.core.banking.account_service.mapper;


import com.core.banking.account_service.dto.AccountDTO;
import com.core.banking.account_service.entity.Account;
import com.core.banking.account_service.entity.Customer;
import com.core.banking.account_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final CustomerRepository customerRepository;

    public Account toAccount(AccountDTO accountDTO){
        Account account = new Account();
        if (accountDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(accountDTO.getCustomerId()).orElse(null);
            account.setCustomerId(customer);
        }
        account.setAccountType(accountDTO.getAccountType());
        account.setBrandAddress(accountDTO.getBrandAddress());
        account.setCreateDate(accountDTO.getCreateDate());
        return account;
    }
}

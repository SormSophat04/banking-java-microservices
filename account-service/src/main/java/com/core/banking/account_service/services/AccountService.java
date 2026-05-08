package com.core.banking.account_service.services;


import com.core.banking.account_service.entity.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);
}

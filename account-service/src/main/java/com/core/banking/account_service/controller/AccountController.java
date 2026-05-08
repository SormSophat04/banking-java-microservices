package com.core.banking.account_service.controller;

import com.core.banking.account_service.dto.AccountDTO;
import com.core.banking.account_service.entity.Account;
import com.core.banking.account_service.mapper.AccountMapper;
import com.core.banking.account_service.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class AccountController {
    public final AccountService accountService;
    public final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO dto) {
        Account account = accountMapper.toAccount(dto);
        account = accountService.create(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<?> getAccounts(){
        List<Account> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
        Account accountById = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountById);
    }
}

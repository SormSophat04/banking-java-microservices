package com.core.banking.laon_service.controller;

import com.core.banking.laon_service.dto.LoanDTO;
import com.core.banking.laon_service.entity.Loan;
import com.core.banking.laon_service.mapper.LoanMapper;
import com.core.banking.laon_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/loans")
public class LoanController {
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @GetMapping
    public ResponseEntity<?> getLoans() {
        List<Loan> loans = loanService.getLoans();
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanDTO dto) {
        Loan loan = loanMapper.toLoan(dto);
        loan = loanService.createLoan(loan);
        return ResponseEntity.ok(loan);
    }

    @GetMapping({"{customerId}"})
    public ResponseEntity<?> getByCustomerId(
            @RequestHeader("banking-ucl-id")  String correlationId,
            @PathVariable Long customerId
    ){
        log.debug("CorrelationId {}", correlationId);
        return ResponseEntity.ok(loanService.getByCustomerId(customerId));
    }
}

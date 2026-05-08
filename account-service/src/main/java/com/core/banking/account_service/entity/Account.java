package com.core.banking.account_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    private String accountType;
    private String brandAddress;
    private LocalDate createDate;
}
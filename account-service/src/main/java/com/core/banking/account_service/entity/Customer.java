package com.core.banking.account_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate createDate;
    private Boolean communicationsSent;
}

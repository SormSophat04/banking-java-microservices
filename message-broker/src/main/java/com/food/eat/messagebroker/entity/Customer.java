package com.food.eat.messagebroker.entity;

import lombok.Data;

@Data
public class Customer {

    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
}

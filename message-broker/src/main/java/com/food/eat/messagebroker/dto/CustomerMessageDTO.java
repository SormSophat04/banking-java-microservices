package com.food.eat.messagebroker.dto;

import lombok.Data;

@Data
public class CustomerMessageDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;
}

package com.core.banking.account_service.mapper;

import com.core.banking.account_service.dto.CustomerDTO;
import com.core.banking.account_service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setCreateDate(dto.getCreateDate());
        return customer;
    }

    public CustomerDTO toCustomerDTO(Customer entity){
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(entity.getCustomerId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

}

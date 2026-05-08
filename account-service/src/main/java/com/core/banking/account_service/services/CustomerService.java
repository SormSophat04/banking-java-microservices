package com.core.banking.account_service.services;


import com.core.banking.account_service.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);

    Customer save(Customer customer);

    void sendCommunication(Customer customer);
    void updateCommunicationSent(Long id);
}

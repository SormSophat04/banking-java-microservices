package com.core.banking.account_service.impl;

import com.core.banking.account_service.dto.CustomerMessageDTO;
import com.core.banking.account_service.entity.Customer;
import com.core.banking.account_service.repository.CustomerRepository;
import com.core.banking.account_service.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public Customer save(Customer customer) {
        customer = customerRepository.save(customer);
        sendCommunication(customer);
        return customer;
    }

    @Override
    public void sendCommunication(Customer customer){
        CustomerMessageDTO customerMessageDTO = new
                CustomerMessageDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
        log.debug("CustomerMessageDTO {}", customerMessageDTO);
        boolean send = streamBridge.send("sendCommunication-out-0", customerMessageDTO);
        log.debug("send {}", send);
    }

    @Override
    public void updateCommunicationSent(Long id){
        Customer customer = getCustomerById(id);
        customer.setCommunicationsSent(true);
        customerRepository.save(customer);
    }
}

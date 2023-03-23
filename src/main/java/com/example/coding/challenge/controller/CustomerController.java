package com.example.coding.challenge.controller;

import com.example.coding.challenge.dto.Customer;
import com.example.coding.challenge.service.CustomerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers() {
        var customers= customerService.retrieveAllCustomers();
        log.info("customers...{}", customers);
        return customers;
    }

    @GetMapping("/customers/count")
    public Long getTotalCustomerCount() {
        return customerService.totalCustomerCount();
    }

    @PostMapping("/customers")
    public Customer createCustomer(@NonNull @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable String id, @NonNull @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/customers/{id}")
    public boolean deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}

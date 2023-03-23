package com.example.coding.challenge.repository;

import com.example.coding.challenge.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmailId(String emailId);

    Customer findByCellNumber(String cellNumber);
}
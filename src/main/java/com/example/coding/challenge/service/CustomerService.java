package com.example.coding.challenge.service;

import com.example.coding.challenge.dto.Customer;
import com.example.coding.challenge.exception.CustomerAlreadyExistsException;
import com.example.coding.challenge.exception.CustomerInvalidException;
import com.example.coding.challenge.exception.CustomerNotFoundException;
import com.example.coding.challenge.repository.CustomerRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(@NonNull CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * This method will count all the customers present in the database.
     *
     * @return Long value for total customer count in database.
     */
    public List<Customer> retrieveAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * This method will count all the customers present in the database.
     *
     * @return Long value for total customer count in database.
     */
    public Long totalCustomerCount() {
        return customerRepository.count();
    }

    /**
     * This will create a new customer once all the validations are successfully checked.
     *
     * @param customer  Non-null customer details
     *
     * @return persisted customer
     */
    public Customer createCustomer(@NonNull Customer customer) {
        checkForValidCustomer(customer);
        checkForCustomerExistingEmailId(customer);
        checkForCustomerExistingCellNumber(customer);
        return customerRepository.save(customer);
    }

    /**
     * This method will update an existing customer once all the validations are successfully checked.
     *
     * @param customerId  Non-null customer id
     * @param customer    Non-null customer details
     *
     * @return persisted/updated customer from DB
     */
    public Customer updateCustomer(@NonNull String customerId, @NonNull Customer customer) {
        checkForValidCustomer(customer);
        var mayBeCustomer = customerRepository.findById(customerId);
        if (mayBeCustomer.isPresent()) {
            var customerFromDB = mayBeCustomer.get();
            customerFromDB.setFirstName(customer.getFirstName());
            customerFromDB.setLastName(customer.getLastName());
            if (Objects.nonNull(customerFromDB.getEmailId()) &&
                    !customerFromDB.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
                checkForCustomerExistingEmailId(customer);
            }
            if (Objects.nonNull(customerFromDB.getCellNumber()) &&
                    !customerFromDB.getCellNumber().equalsIgnoreCase(customer.getCellNumber())) {
                checkForCustomerExistingCellNumber(customer);
            }
            customerFromDB.setEmailId(customer.getEmailId());
            customerFromDB.setCellNumber(customer.getCellNumber());
            return customerRepository.save(customerFromDB);
        }
        throw new CustomerNotFoundException("Could not find customer with Id=" + customerId);
    }

    /**
     * This method will delete the customer from database based on the customer id.
     *
     * @param customerId Non-null customer id.
     *
     * @return true, if customer record is deleted. Otherwise, false.
     */
    public boolean deleteCustomer(@NonNull String customerId) {
        var mayBeCustomer = customerRepository.findById(customerId);
        if (mayBeCustomer.isPresent()) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    private void checkForValidCustomer(@NonNull Customer customer) {
        var firstName = customer.getFirstName();
        if (Objects.nonNull(firstName) && firstName.length() > 100) {
            throw new CustomerInvalidException("Invalid Customer with first name greater than 100 characters.");
        }
        var lastName = customer.getLastName();
        if (Objects.nonNull(lastName) && lastName.length() > 100) {
            throw new CustomerInvalidException("Invalid Customer with last name greater than 100 characters.");
        }
        var cellNumber = customer.getCellNumber();
        if (Objects.nonNull(cellNumber) && (cellNumber.length() > 10)) {
            throw new CustomerInvalidException("Invalid Customer with cell number greater than 10 digits.");
        }
        try {
            Long.valueOf(cellNumber);
        } catch (NumberFormatException nfe) {
            throw new CustomerInvalidException("Invalid Customer with cell number containing other than digits.");
        }
        var emailId = customer.getEmailId();
        if (Objects.nonNull(emailId) && emailId.length() > 320) {
            throw new CustomerInvalidException("Invalid Customer with email id greater than 320 characters.");
        }
    }

    private void checkForCustomerExistingEmailId(@NonNull Customer customer) {
        var customerByEmailId = customerRepository.findByEmailId(customer.getEmailId());
        if (Objects.nonNull(customerByEmailId)) {
            throw new CustomerAlreadyExistsException("Customer already present with email id=" + customer.getEmailId());
        }
    }

    private void checkForCustomerExistingCellNumber(@NonNull Customer customer) {
        var customerByCellNumber = customerRepository.findByCellNumber(customer.getCellNumber());
        if (Objects.nonNull(customerByCellNumber)) {
            throw new CustomerAlreadyExistsException("Customer already present with cell number=" + customer.getCellNumber());
        }
    }
}

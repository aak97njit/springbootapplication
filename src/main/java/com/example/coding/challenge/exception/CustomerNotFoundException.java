package com.example.coding.challenge.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String exception) {
        super(exception);
    }
}

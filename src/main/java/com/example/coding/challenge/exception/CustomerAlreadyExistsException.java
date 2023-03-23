package com.example.coding.challenge.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String exception) {
        super(exception);
    }
}

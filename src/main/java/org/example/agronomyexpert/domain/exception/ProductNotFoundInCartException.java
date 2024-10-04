package org.example.agronomyexpert.domain.exception;

public class ProductNotFoundInCartException extends RuntimeException {

    public ProductNotFoundInCartException(String message) {
        super(message);
    }
}
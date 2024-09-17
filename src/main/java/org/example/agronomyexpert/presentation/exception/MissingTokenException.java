package org.example.agronomyexpert.presentation.exception;

public class MissingTokenException extends RuntimeException {

    public MissingTokenException(String message) {
        super(message);
    }
}
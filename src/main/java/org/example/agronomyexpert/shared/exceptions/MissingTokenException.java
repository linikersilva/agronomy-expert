package org.example.agronomyexpert.shared.exceptions;

public class MissingTokenException extends RuntimeException {

    public MissingTokenException(String message) {
        super(message);
    }
}
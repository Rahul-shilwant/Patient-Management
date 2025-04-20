package com.pm.patientservice.exception;

public class EmailAlreadyExixstException extends RuntimeException {
    public EmailAlreadyExixstException(String message) {
        super(message);
    }
}

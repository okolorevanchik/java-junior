package com.acme.edu.exceptions;

public class WritingToDatabaseException extends LogWritingException {

    public WritingToDatabaseException(String message) {
        super(message);
    }
}

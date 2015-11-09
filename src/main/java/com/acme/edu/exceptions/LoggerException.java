package com.acme.edu.exceptions;

public class LoggerException extends Exception {

    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(Exception e) {
        super(e);
    }
}

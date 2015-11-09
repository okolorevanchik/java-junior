package com.acme.edu.exceptions;

public class LoggerServerException extends Exception {

    public LoggerServerException(Exception e) {
        super(e);
    }
}

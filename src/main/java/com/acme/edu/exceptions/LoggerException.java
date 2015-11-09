package com.acme.edu.exceptions;

/**
 * Class exceptions for logger.
 */
public class LoggerException extends Exception {

    /**
     * Initializes an exception
     *
     * @param message Error message
     */
    public LoggerException(String message) {
        super(message);
    }

    /**
     * Initializes an exception
     *
     * @param e Object exceptions are forwarding
     */
    public LoggerException(Exception e) {
        super(e);
    }
}

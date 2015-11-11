package com.acme.edu.exceptions;

/**
 * Class exceptions for server logging.
 */
public class LoggerServerException extends Exception {

    /**
     * Initializes an exception
     *
     * @param e Object exceptions are forwarding
     */
    public LoggerServerException(Exception e) {
        super(e);
    }
}

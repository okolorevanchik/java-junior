package com.acme.edu.exceptions;

/**
 * Exception error output data in any format.
 */
public class PrintDataException extends LoggerException {

    /**
     * Initializes an exception
     *
     * @param e Object exceptions are forwarding
     */
    public PrintDataException(Exception e) {
        super(e);
    }

    /**
     * Initializes an exception
     *
     * @param message Error message
     */
    public PrintDataException(String message) {
        super(message);
    }
}

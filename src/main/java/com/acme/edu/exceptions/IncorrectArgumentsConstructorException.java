package com.acme.edu.exceptions;

/**
 * Thrown when transferring to the constructor arguments are not valid.
 */
public class IncorrectArgumentsConstructorException extends LoggerException {

    /**
     * Initializes an exception
     *
     * @param message Error message
     */
    public IncorrectArgumentsConstructorException(String message) {
        super(message);
    }
}

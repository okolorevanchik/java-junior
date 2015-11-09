package com.acme.edu.exceptions;

/**
 * Thrown when transferring to the method arguments are not valid.
 */
public class IncorrectInputsParametersMethodException extends LoggerException {

    /**
     * Initializes an exception
     *
     * @param message Error message
     */
    public IncorrectInputsParametersMethodException(String message) {
        super(message);
    }
}

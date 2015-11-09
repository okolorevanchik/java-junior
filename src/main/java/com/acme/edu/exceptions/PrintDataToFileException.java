package com.acme.edu.exceptions;

/**
 * Exception error output to a file.
 */
public class PrintDataToFileException extends PrintDataException {

    /**
     * Initializes an exception
     *
     * @param e Object exceptions are forwarding
     */
    public PrintDataToFileException(Exception e) {
        super(e);
    }
}

package com.acme.edu.exceptions;

/**
 * Exception Error sending data to the server.
 */
public class PrintDataToNetworkException extends PrintDataException {

    /**
     * Initializes an exception
     *
     * @param e Object exceptions are forwarding
     */
    public PrintDataToNetworkException(Exception e) {
        super(e);
    }
}

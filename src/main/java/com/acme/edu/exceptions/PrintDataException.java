package com.acme.edu.exceptions;

public class PrintDataException extends LoggerException {

    public PrintDataException(String message) {
        super(message);
    }

    public PrintDataException(Exception e) {
        super(e);
    }
}

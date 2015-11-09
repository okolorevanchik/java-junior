package com.acme.edu.exceptions;

public class PrintDataToFileException extends PrintDataException {

    public PrintDataToFileException(String message) {
        super(message);
    }

    public PrintDataToFileException(Exception e) {
        super(e);
    }
}

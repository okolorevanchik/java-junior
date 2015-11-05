package com.acme.edu.exceptions;

public class SendingDataOverNetworkException extends LogWritingException {

    public SendingDataOverNetworkException(String message) {
        super(message);
    }
}

package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.LogWritingException;
import com.acme.edu.printers.Printable;

public class IntBufferState extends State {

    private static final String PRIMITIVE_PREFIX = "primitive: %s";

    private int buffer = 0;

    public IntBufferState(Printable printable, Decorate decorate) {
        super(decorate, printable);
    }

    @Override
    public void flush() throws LogWritingException {
        getPrintable().print(getDecorate().getDecorateString(PRIMITIVE_PREFIX, String.valueOf(buffer)));
        buffer = 0;
    }

    @Override
    public void log(String message) throws LogWritingException {
        int intMessage = Integer.parseInt(message);
        if (checkOverflow(buffer + (long) intMessage)) {
            getPrintable().print(getDecorate().getDecorateString(PRIMITIVE_PREFIX, String.valueOf(buffer)));
            buffer = intMessage;
        } else {
            buffer += intMessage;
        }
    }

    private boolean checkOverflow(long message) {
        return message > Integer.MAX_VALUE || message < Integer.MIN_VALUE;
    }
}

package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class IntBufferState extends State {

    private static final String PRIMITIVE_PREFIX = "primitive: %s";

    private int buffer = 0;

    public IntBufferState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }

    @Override
    public void flush(boolean isClosed) throws PrintDataException {
        String result = getDecorate().getDecorateString(PRIMITIVE_PREFIX, String.valueOf(buffer));
        printAll(result, isClosed);
        buffer = 0;
    }

    @Override
    public void log(String message) throws PrintDataException {
        int intMessage = Integer.parseInt(message);
        if (checkOverflow(buffer + (long) intMessage)) {
            String result = getDecorate().getDecorateString(PRIMITIVE_PREFIX, String.valueOf(buffer));
            printAll(result, false);
            buffer = intMessage;
        } else {
            buffer += intMessage;
        }
    }

    private boolean checkOverflow(long message) {
        return message > Integer.MAX_VALUE || message < Integer.MIN_VALUE;
    }
}

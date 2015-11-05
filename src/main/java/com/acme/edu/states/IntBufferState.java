package com.acme.edu.states;

import com.acme.edu.Printable;

public class IntBufferState implements State {

    private static final String PRIMITIVE_PREFIX = "primitive: ";

    private int buffer = 0;
    private Printable printable;

    public IntBufferState(Printable printable) {
        this.printable = printable;
    }

    @Override
    public void flush() {
        printable.print(PRIMITIVE_PREFIX + buffer);
        buffer = 0;
    }

    @Override
    public void log(String message) {
        int intMessage = Integer.parseInt(message);
        if (checkOverflow(buffer + (long) intMessage)) {
            printable.print(PRIMITIVE_PREFIX + buffer);
            buffer = intMessage;
        } else {
            buffer += intMessage;
        }
    }

    private boolean checkOverflow(long message) {
        return message > Integer.MAX_VALUE || message < Integer.MIN_VALUE;
    }
}

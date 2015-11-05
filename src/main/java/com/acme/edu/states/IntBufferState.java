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
        if (buffer == 0) {
            return;
        }
        printable.print(PRIMITIVE_PREFIX + buffer);
        buffer = 0;
    }

    @Override
    public void log(String message) {
        int intMessage = Integer.parseInt(message);
        if (intMessage != 0 && intMessage < Integer.MAX_VALUE) {
            summing(intMessage);
        } else if (intMessage == Integer.MAX_VALUE) {
            printable.print(PRIMITIVE_PREFIX + buffer);
            printable.print(PRIMITIVE_PREFIX + Integer.MAX_VALUE);
            buffer = 0;
        } else if (buffer == 0) {
            printable.print(PRIMITIVE_PREFIX + 0);
        } else {
            printable.print(PRIMITIVE_PREFIX + message);
        }
    }

    private void summing(int message) {
        int temp = buffer;
        buffer += message;
        if (buffer < temp && message > 0) {
            printable.print(PRIMITIVE_PREFIX + temp);
            buffer = message;
        }
    }
}

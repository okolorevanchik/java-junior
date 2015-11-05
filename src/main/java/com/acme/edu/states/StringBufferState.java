package com.acme.edu.states;

import com.acme.edu.Printable;

public class StringBufferState implements State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%d)";
    private static final String STRING_PREFIX = "string: ";

    private String buffer = "";
    private int count = 1;
    private Printable printable;

    public StringBufferState(Printable printable) {
        this.printable = printable;
    }


    @Override
    public void flush() {
        if (buffer.isEmpty()) {
            return;
        }

        if (count > 1) {
            printable.print(String.format(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, buffer, count));
            count = 1;
        } else {
            printable.print(STRING_PREFIX + buffer);
        }
        buffer = "";
    }

    @Override
    public void log(String message) {
        if (buffer.equals(message)) {
            count++;
        } else {
            flush();
            buffer = message;
        }
    }
}

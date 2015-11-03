package com.acme.edu.states;

import com.acme.edu.Printable;

public class StringState implements State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%d)";
    private static final String STRING_PREFIX = "string: ";

    private String buffer = "";
    private int count = 1;
    private Printable printable;

    public StringState(Printable printable) {
        this.printable = printable;
    }


    @Override
    public void displayBuffer() {
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
    public void cleanOrCommutationBuffer(String message) {
        if (buffer.equals(message)) {
            count++;
        } else {
            displayBuffer();
            buffer = message;
        }
    }
}

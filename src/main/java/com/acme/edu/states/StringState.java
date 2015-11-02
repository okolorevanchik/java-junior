package com.acme.edu.states;

import com.acme.edu.Printer;

public class StringState extends State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%d)";
    private static final String STRING_PREFIX = "string: ";

    private String buffer = "";
    private int count = 1;

    public StringState(Printer printer) {
        super(printer);
    }


    @Override
    public void displayBuffer() {
        if (buffer.isEmpty()) {
            return;
        }

        if (count > 1) {
            getPrinter().print(String.format(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, buffer, count));
            count = 1;
        } else {
            getPrinter().print(STRING_PREFIX + buffer);
        }
        buffer = "";
    }

    @Override
    protected void editBuffer(String message) {
        if (buffer.equals(message)) {
            count++;
        } else {
            displayBuffer();
            buffer = message;
        }
    }
}

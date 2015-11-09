package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class StringBufferState extends State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%s)";
    private static final String STRING_PREFIX = "string: %s";

    private String buffer = "";
    private int count = 1;

    public StringBufferState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }


    @Override
    public void flush(boolean isClosed) throws PrintDataException {
        if (buffer.isEmpty()) {
            return;
        }

        String result;
        if (count > 1) {
            result = getDecorate()
                    .getDecorateString(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, buffer, String.valueOf(count));
            count = 1;
        } else {
            result = getDecorate().getDecorateString(STRING_PREFIX, buffer);
        }
        printAll(result, isClosed);
        buffer = "";
    }

    @Override
    public void log(String message) throws PrintDataException {
        if (buffer.equals(message)) {
            count++;
        } else {
            flush(false);
            buffer = message;
        }
    }
}

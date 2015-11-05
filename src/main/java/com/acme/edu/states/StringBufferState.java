package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.LogWritingException;
import com.acme.edu.printers.Printable;

public class StringBufferState extends State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%s)";
    private static final String STRING_PREFIX = "string: %s";

    private String buffer = "";
    private int count = 1;

    public StringBufferState(Printable printable, Decorate decorate) {
        super(decorate, printable);
    }


    @Override
    public void flush() throws LogWritingException {
        if (buffer.isEmpty()) {
            return;
        }

        if (count > 1) {
            getPrintable().print(getDecorate()
                    .getDecorateString(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, buffer, String.valueOf(count)));
            count = 1;
        } else {
            getPrintable().print(getDecorate().getDecorateString(STRING_PREFIX, buffer));
        }
        buffer = "";
    }

    @Override
    public void log(String message) throws LogWritingException {
        if (buffer.equals(message)) {
            count++;
        } else {
            flush();
            buffer = message;
        }
    }
}

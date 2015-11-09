package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

/**
 * Specially state for string type
 */
public class StringBufferState extends State {

    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%s)";
    private static final String STRING_PREFIX = "string: %s";

    private String buffer = "";
    private int count = 1;

    /**
     * Init type of print fo log
     *
     * @param decorate  The object sets the output.
     * @param printable Object is responsible for the output of information to a particular source.
     */
    public StringBufferState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }


    /**
     * Check string counter for correctly log strings
     *
     * @param isClosed It serves as a pointer to complete the work with the logger.
     */
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

    /**
     * Check string buffer, accumulate string counter
     * in previous and this stings equals else log string
     *
     * @param message string value
     */
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

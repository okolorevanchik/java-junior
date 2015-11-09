package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

/**
 * Specially state for integer values
 */
public class IntBufferState extends State {

    private static final String PRIMITIVE_PREFIX = "primitive: %s";

    private int buffer = 0;

    /**
     * Init type of print fo log
     *
     * @param decorate  The object sets the output.
     * @param printable Object is responsible for the output of information to a particular source.
     */
    public IntBufferState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }

    /**
     * Check and log buffer
     *
     * @param isClosed It serves as a pointer to complete the work with the logger.
     */
    @Override
    public void flush(boolean isClosed) throws PrintDataException {
        String result = getDecorate().getDecorateString(PRIMITIVE_PREFIX, String.valueOf(buffer));
        printAll(result, isClosed);
        buffer = 0;
    }

    /**
     * Log integer value
     * Buffer is the sum of integer variables which come to this method
     * If intSum > Integer.MAX_VALUE or intSum < Integer.MIN_VALUE, method
     * log sum before accumulate Integer.MAX_VALUE/Integer.MIN_VALUE and message
     *
     * @param message Integer value
     */
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

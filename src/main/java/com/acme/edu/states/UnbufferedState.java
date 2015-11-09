package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

/**
 * Specially state for char, boolean and object types
 * It has no buffer
 * Log all types like a string type without accumulate counter of strings
 */
public class UnbufferedState extends State {

    /**
     * Init type of print fo log
     *
     * @param decorate  The object sets the output.
     * @param printable Object is responsible for the output of information to a particular source.
     */
    public UnbufferedState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }

    /**
     * Log all types like a string
     *
     * @param message String value
     */
    @Override
    public void log(String message) throws PrintDataException {
        printAll(message, false);
    }
}

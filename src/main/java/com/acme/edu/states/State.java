package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

/**
 * Abstract class for State
 */
public abstract class State {

    private Decorate decorate;
    private Printable[] printables;

    /**
     * Init type of print fo log
     *
     * @param decorate  The object sets the output.
     * @param printable Object is responsible for the output of information to a particular source.
     */
    public State(Decorate decorate, Printable... printable) {
        this.decorate = decorate;
        this.printables = printable;
    }

    protected void printAll(String message, boolean flush) throws PrintDataException {
        for (Printable printable : printables) {
            printable.print(message, flush);
        }
    }

    /**
     * Returns the current state of the decorator.
     *
     * @return Current decorator
     */
    public Decorate getDecorate() {
        return decorate;
    }

    /**
     * Empty method of purification buffer status. States have buffer override this method.
     *
     * @param isClosed It serves as a pointer to complete the work with the logger.
     * @throws PrintDataException
     */
    public void flush(boolean isClosed) throws PrintDataException {
    }

    /**
     * Logged transmitted message
     *
     * @param message String thar will be logged
     * @throws PrintDataException
     */
    public abstract void log(String message) throws PrintDataException;
}

package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public abstract class State {

    private Decorate decorate;
    private Printable[] printables;

    public State(Decorate decorate, Printable... printable) {
        this.decorate = decorate;
        this.printables = printable;
    }

    protected void printAll(String message) throws PrintDataException {
        for (Printable printable: printables) {
            printable.print(message);
        }
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public void flush() throws PrintDataException {
    }

    public abstract void log(String message) throws PrintDataException;
}

package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.printers.Printable;

public abstract class State {

    private Decorate decorate;
    private Printable printable;

    public State(Decorate decorate, Printable printable) {
        this.decorate = decorate;
        this.printable = printable;
    }

    public Printable getPrintable() {
        return printable;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public abstract void flush() throws LoggerException;

    public abstract void log(String message) throws LoggerException;
}

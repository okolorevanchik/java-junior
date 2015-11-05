package com.acme.edu.states;

import com.acme.edu.Printable;

public class UnbufferedState implements State {

    private Printable printable;

    public UnbufferedState(Printable printable) {
        this.printable = printable;
    }

    @Override
    public void flush() {
    }

    @Override
    public void log(String message) {
        printable.print(message);
    }
}

package com.acme.edu.states;

import com.acme.edu.Printable;

public abstract class State {

    private Printable printable;

    public State(Printable printable) {
        this.printable = printable;
    }

    protected Printable getPrintable() {
        return printable;
    }

    public abstract void displayBuffer();

    public abstract void cleanOrCommutationBuffer(String message);

}

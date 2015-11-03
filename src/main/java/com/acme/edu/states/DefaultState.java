package com.acme.edu.states;

import com.acme.edu.Printable;

public class DefaultState implements State {

    private Printable printable;

    public DefaultState(Printable printable) {
        this.printable = printable;
    }

    @Override
    public void displayBuffer() {
    }

    @Override
    public void cleanOrCommutationBuffer(String message) {
        printable.print(message);
    }
}

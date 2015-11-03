package com.acme.edu.states;

import com.acme.edu.Printable;

public class DefaultState extends State {

    public DefaultState(Printable printable) {
        super(printable);
    }

    @Override
    public void displayBuffer() {
    }

    @Override
    public void cleanOrCommutationBuffer(String message) {
        getPrintable().print(message);
    }
}

package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.printers.Printable;

public class UnbufferedState extends State {

    public UnbufferedState(Printable printable, Decorate decorate) {
        super(decorate, printable);
    }

    @Override
    public void flush() {
    }

    @Override
    public void log(String message) {
        getPrintable().print(message);
    }
}

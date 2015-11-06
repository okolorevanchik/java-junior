package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class UnbufferedState extends State {

    public UnbufferedState(Decorate decorate, Printable... printable) {
        super(decorate, printable);
    }

    @Override
    public void log(String message) throws PrintDataException {
        printAll(message);
    }
}

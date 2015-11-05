package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class UnbufferedState extends State {

    public UnbufferedState(Printable printable, Decorate decorate) {
        super(decorate, printable);
    }

    @Override
    public void log(String message) throws PrintDataException {
        getPrintable().print(message);
    }
}

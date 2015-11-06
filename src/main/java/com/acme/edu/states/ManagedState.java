package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class ManagedState {

    private State numberState;
    private State stringState;
    private State defaultState;

    public ManagedState(Printable printable, Decorate decorate) throws IncorrectArgumentsConstructorException {
        if (printable == null || decorate == null) {
            throw new IncorrectArgumentsConstructorException("Constructor parameter can not be null.");
        }
        this.numberState = new IntBufferState(printable, decorate);
        this.stringState = new StringBufferState(printable, decorate);
        this.defaultState = new UnbufferedState(printable, decorate);
    }

    public State getIntBufferState(State currentState) throws PrintDataException {
        printBuffer(currentState, numberState);
        return numberState;
    }

    public State getStringBufferState(State currentState) throws PrintDataException {
        printBuffer(currentState, stringState);
        return stringState;
    }

    public State getUnbufferedState(State currentState) throws PrintDataException {
        printBuffer(currentState, defaultState);
        return defaultState;
    }

    private void printBuffer(State currentState, State checkedState) throws PrintDataException {
        if (currentState != null && currentState != checkedState) {
            currentState.flush();
        }
    }
}

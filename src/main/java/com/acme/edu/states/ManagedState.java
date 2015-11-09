package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

public class ManagedState {

    private State numberState;
    private State stringState;
    private State defaultState;

    public ManagedState(Decorate decorate, Printable... printable) throws IncorrectArgumentsConstructorException {
        if (printable == null || decorate == null || checkNullVarargsPrintable(printable)) {
            throw new IncorrectArgumentsConstructorException("Constructor parameter can not be null.");
        }
        this.numberState = new IntBufferState(decorate, printable);
        this.stringState = new StringBufferState(decorate, printable);
        this.defaultState = new UnbufferedState(decorate, printable);
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
        if (currentState != checkedState) {
            currentState.flush(false);
        }
    }

    private boolean checkNullVarargsPrintable(Printable... printables) throws IncorrectArgumentsConstructorException {
        for (Printable printable : printables) {
            if (printable == null)
                return true;
        }
        return false;
    }
}

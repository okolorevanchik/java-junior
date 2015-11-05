package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.printers.Printable;

public class ManagedState {

    private State numberState;
    private State stringState;
    private State defaultState;

    public ManagedState(Printable printable, Decorate decorate) {
        this.numberState = new IntBufferState(printable, decorate);
        this.stringState = new StringBufferState(printable, decorate);
        this.defaultState = new UnbufferedState(printable, decorate);
    }

    public State getNumberState(State currentState) {
        printBuffer(currentState, numberState);
        return numberState;
    }

    public State getStringState(State currentState) {
        printBuffer(currentState, stringState);
        return stringState;
    }

    public State getDefaultState(State currentState) {
        printBuffer(currentState, defaultState);
        return defaultState;
    }

    private void printBuffer(State currentState, State checkedState) {
        if (currentState != null && currentState != checkedState) {
            currentState.flush();
        }
    }
}

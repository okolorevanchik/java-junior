package com.acme.edu;

import com.acme.edu.states.UnbufferedState;
import com.acme.edu.states.IntBufferState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringBufferState;

public class StateFactory {

    private State numberState;
    private State stringState;
    private State defaultState;

    public StateFactory(Printable printable) {
        this.numberState = new IntBufferState(printable);
        this.stringState = new StringBufferState(printable);
        this.defaultState = new UnbufferedState(printable);
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

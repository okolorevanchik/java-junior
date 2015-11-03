package com.acme.edu;

import com.acme.edu.states.DefaultState;
import com.acme.edu.states.NumberState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringState;

public class StateFactory {

    private State numberState;
    private State stringState;
    private State defaultState;

    public StateFactory(Printable printable) {
        this.numberState = new NumberState(printable);
        this.stringState = new StringState(printable);
        this.defaultState = new DefaultState(printable);
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
            currentState.displayBuffer();
        }
    }
}

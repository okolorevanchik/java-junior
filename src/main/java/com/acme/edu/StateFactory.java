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
        if (currentState != null && currentState != numberState) {
            currentState.displayBuffer();
        }
        return numberState;
    }

    public State getStringState(State currentState) {
        if (currentState != null && currentState != stringState) {
            currentState.displayBuffer();
        }
        return stringState;
    }

    public State getDefaultState(State currentState) {
        if (currentState != null && currentState != defaultState) {
            currentState.displayBuffer();
        }
        return defaultState;
    }
}

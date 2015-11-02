package com.acme.edu;

import com.acme.edu.states.NumberState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringState;

public class StateFactory {

    private State numberState;
    private State stringState;

    public StateFactory(Printer printer) {
        this.numberState = new NumberState(printer);
        this.stringState = new StringState(printer);
    }

    public State getNumberState(State currentState) {
        if (currentState != null && currentState != numberState) {
            currentState.displayBuffer();
        }
        return numberState;
    }

    public State getStringState(State currentState) {
        if (currentState!= null && currentState != stringState) {
            currentState.displayBuffer();
        }
        return stringState;
    }
}

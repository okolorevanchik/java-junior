package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.SendingDataOverNetworkException;
import com.acme.edu.exceptions.WritingDataToFileException;
import com.acme.edu.printers.Printable;

public class ManagedState {

    private State numberState;
    private State stringState;
    private State defaultState;

    public ManagedState(Printable printable, Decorate decorate) {
        if (printable == null || decorate == null) {
            throw new IncorrectArgumentsConstructorException();
        }
        this.numberState = new IntBufferState(printable, decorate);
        this.stringState = new StringBufferState(printable, decorate);
        this.defaultState = new UnbufferedState(printable, decorate);
    }

    public State getNumberState(State currentState) throws WritingDataToFileException, SendingDataOverNetworkException {
        printBuffer(currentState, numberState);
        return numberState;
    }

    public State getStringState(State currentState) throws WritingDataToFileException, SendingDataOverNetworkException {
        printBuffer(currentState, stringState);
        return stringState;
    }

    public State getDefaultState(State currentState) throws WritingDataToFileException, SendingDataOverNetworkException {
        printBuffer(currentState, defaultState);
        return defaultState;
    }

    private void printBuffer(State currentState, State checkedState) throws WritingDataToFileException, SendingDataOverNetworkException {
        if (currentState != null && currentState != checkedState) {
            currentState.flush();
        }
    }
}

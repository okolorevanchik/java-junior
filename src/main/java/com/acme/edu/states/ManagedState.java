package com.acme.edu.states;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.PrintDataException;
import com.acme.edu.printers.Printable;

/**
 * Responsible for the switching states during a logger.
 */
public class ManagedState {

    private State numberState;
    private State stringState;
    private State defaultState;

    /**
     * Initialize the object state manager.
     *
     * @param decorate  The object sets the output.
     * @param printable Object is responsible for the output of information to a particular source.
     * @throws IncorrectArgumentsConstructorException
     */
    public ManagedState(Decorate decorate, Printable... printable) throws IncorrectArgumentsConstructorException {
        if (printable == null || decorate == null || checkNullVarargsPrintable(printable)) {
            throw new IncorrectArgumentsConstructorException("Constructor parameter can not be null.");
        }
        this.numberState = new IntBufferState(decorate, printable);
        this.stringState = new StringBufferState(decorate, printable);
        this.defaultState = new UnbufferedState(decorate, printable);
    }

    /**
     * Switch the state to work with numbers and displays the previous buffer state.
     *
     * @param currentState The current status of the logger.
     * @return Status logger for working with numbers.
     * @throws PrintDataException
     */
    public State getIntBufferState(State currentState) throws PrintDataException {
        printBuffer(currentState, numberState);
        return numberState;
    }

    /**
     * Switch the state to work with strings and displays the previous buffer state.
     *
     * @param currentState The current status of the logger.
     * @return Status logger for working with strings.
     * @throws PrintDataException
     */
    public State getStringBufferState(State currentState) throws PrintDataException {
        printBuffer(currentState, stringState);
        return stringState;
    }

    /**
     * Switch the logger to the state to work with other input data.
     *
     * @param currentState The current status of the logger.
     * @return Status logger for working with other input data.
     * @throws PrintDataException
     */
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

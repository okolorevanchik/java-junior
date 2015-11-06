package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.GetStateException;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.ManagedState;
import com.acme.edu.states.State;
import com.acme.edu.states.UnbufferedState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ManagedStateTest {

    private Printable printable;
    private Decorate decorate;

    @Before
    public void setUp() {
        printable = mock(Printable.class);
        decorate = mock(Decorate.class);
    }

    @Test(expected = IncorrectArgumentsConstructorException.class)
    public void shouldThrowIncorrectArgumentsConstructorExceptionWhenFirstArgumentsIsNull() throws Exception {
        new ManagedState(null, decorate);
    }

    @Test(expected = IncorrectArgumentsConstructorException.class)
    public void shouldThrowIncorrectArgumentsConstructorExceptionWhenSecondArgumentsIsNull() throws Exception {
        new ManagedState(printable, null);
    }

    @Test(expected = GetStateException.class)
    public void shouldThrowGetStateException() throws Exception {
        ManagedState managedState = new ManagedState(printable, decorate);

        managedState.getDefaultState(null);
    }

    @Test
    public void shouldCollMethodFlushFromCurrentStateWhenCurrentStateNotEqualNewState() throws Exception {
        ManagedState managedState = new ManagedState(printable, decorate);
        State currentState = mock(UnbufferedState.class);

        managedState.getNumberState(currentState);

        verify(currentState, times(1)).flush();
    }

}

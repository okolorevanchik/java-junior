package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.GetStateException;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ManagedStateTest {

    private Printable printable;
    private Decorate decorate;
    private State currentState;
    private ManagedState managedState;

    @Before
    public void setUp() throws Exception {
        printable = mock(Printable.class);
        decorate = mock(Decorate.class);
        currentState = mock(State.class);
        managedState = new ManagedState(printable, decorate);
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
        managedState.getDefaultState(null);
    }

    @Test
    public void shouldCallMethodFlushFromCurrentStateWhenCurrentStateNotEqualNewState() throws Exception {
        managedState.getNumberState(currentState);

        verify(currentState, times(1)).flush();
    }

    @Test
    public void shouldNotCallMethodFlushFromCurrentStateWhenCurrentStateEqualNewState() throws Exception {
        State newCurrentState = managedState.getDefaultState(currentState);
        State newCurrentStateMock = mock(newCurrentState.getClass());
        managedState.getDefaultState(newCurrentState);

        verify(newCurrentStateMock, times(0)).flush();
    }

    @Test
    public void shouldGetIntBufferState() throws Exception {
        State actualState = managedState.getNumberState(currentState);

        assertTrue(actualState instanceof IntBufferState);
    }

    @Test
    public void shouldGetStringBufferState() throws Exception {
        State actualState = managedState.getStringState(currentState);

        assertTrue(actualState instanceof StringBufferState);
    }

    @Test
    public void shouldGetUnbufferedState() throws Exception {
        State actualState = managedState.getDefaultState(currentState);

        assertTrue(actualState instanceof UnbufferedState);
    }
}

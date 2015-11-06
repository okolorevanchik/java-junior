package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.GetStateException;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.ManagedState;
import com.acme.edu.states.State;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ManagedStateTest {

    private static final String PRIMITIVE_PREFIX = "primitive: %s";

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
    public void shouldCallMethodFlushFromCurrentStateWhenCurrentStateNotEqualNewState() throws Exception {
        ManagedState managedState = new ManagedState(printable, decorate);
        State currentState = mock(State.class);

        managedState.getNumberState(currentState);

        verify(currentState, times(1)).flush();
    }

    @Test
    public void shouldNotCallMethodFlushFromCurrentStateWhenCurrentStateEqualNewState() throws Exception {
        ManagedState managedState = new ManagedState(printable, decorate);
        State currentState = mock(State.class);

        State newCurrentState = managedState.getDefaultState(currentState);
        State newCurrentStateMock = mock(newCurrentState.getClass());

        verify(newCurrentStateMock, times(0)).flush();
    }

}

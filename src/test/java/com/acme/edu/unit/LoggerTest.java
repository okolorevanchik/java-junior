package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.states.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoggerTest {

    private ManagedState managedState;

    @Before
    public void setUp() throws Exception {
        managedState = mock(ManagedState.class);
        State state = mock(State.class);

        when(managedState.getIntBufferState(state)).thenReturn(mock(IntBufferState.class));
        when(managedState.getStringBufferState(state)).thenReturn(mock(StringBufferState.class));
        when(managedState.getUnbufferedState(state)).thenReturn(mock(UnbufferedState.class));
    }

    @Test(expected = IncorrectArgumentsConstructorException.class)
    public void shouldThrowIncorrectArgumentsConstructorExceptionWhenInputParameterOfConstructorIsNull() throws Exception {
        new Logger(null);
    }


}

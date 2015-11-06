package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.ManagedState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

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

}

package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.Logger;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.IncorrectInputsParametersMethodException;
import com.acme.edu.states.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoggerTest {

    private static final String SEP = System.lineSeparator();
    private static final String NOT_PREFIX = "%s";
    private static final String CHAR_PREFIX = "char: %s";
    private static final String PRIMITIVE_PREFIX = "primitive: %s";
    private static final String PRIMITIVES_MATRIX_PREFIX = "primitives matrix: {" + SEP + "%s}";
    private static final String PRIMITIVES_MULTIMATRIX_PREFIX = "primitives multimatrix: {" + SEP + "%s}";
    private static final String REFERENCE_PREFIX = "reference: %s";
    private static final String OPEN_BRACKET = "{";
    private static final String CLOSE_BRACKET = "}";

    private ManagedState managedState;
    private State intBufferState;
    private State stringBufferState;
    private State unbufferedState;
    private State defaultState;
    private Decorate decorate;

    @Before
    public void setUp() throws Exception {
        decorate = mock(Decorate.class);
        managedState = mock(ManagedState.class);
        intBufferState = mock(IntBufferState.class);
        stringBufferState = mock(StringBufferState.class);
        unbufferedState = mock(UnbufferedState.class);
        defaultState = mock(State.class);

//        when(managedState.getIntBufferState(defaultState)).thenReturn(intBufferState);
//        when(managedState.getStringBufferState(defaultState)).thenReturn(stringBufferState);
//        when(managedState.getUnbufferedState(defaultState)).thenReturn(unbufferedState);
//
//        when(managedState.getStringBufferState(null)).thenReturn(stringBufferState);
//        when(managedState.getUnbufferedState(null)).thenReturn(unbufferedState);
    }

    @Test(expected = IncorrectArgumentsConstructorException.class)
    public void shouldThrowIncorrectArgumentsConstructorExceptionWhenInputParameterOfConstructorIsNull() throws Exception {
        new Logger(null);
    }

    @Test(expected = IncorrectInputsParametersMethodException.class)
    public void shouldThrowIncorrectInputsParametersMethodExceptionWhenInputParameterStringIsNullForMethodLog() throws Exception {
        Logger logger = new Logger(managedState);
        String testString = null;

        logger.log(testString);
    }

    @Test(expected = IncorrectInputsParametersMethodException.class)
    public void shouldThrowIncorrectInputsParametersMethodExceptionWhenInputParameterVarargIntsIsNullForMethodLog() throws Exception {
        Logger logger = new Logger(managedState);
        int[] nullableArray = null;

        logger.log(nullableArray);
    }

    @Test(expected = IncorrectInputsParametersMethodException.class)
    public void shouldThrowIncorrectInputsParametersMethodExceptionWhenInputParameterStringIsEmptyForMethodLog() throws Exception {
        Logger logger = new Logger(managedState);

        logger.log("");
    }

    @Test
    public void shouldCallMethodLogClassStateForInputParameterIsInt() throws Exception {
        Logger logger = new Logger(managedState);
        when(managedState.getIntBufferState(any())).thenReturn(intBufferState);

        logger.log(1);

        verify(intBufferState, times(1)).log("1");
    }

    @Test
    public void shouldCallMethodLogClassStateForInputParameterIsChar() throws Exception {
        Logger logger = new Logger(managedState);
        whenConditionForUnbufferedInputParameters(CHAR_PREFIX, "char: q", "q");

        logger.log('q');

        verify(defaultState, times(1)).log("char: q");
        verify(defaultState, times(1)).getDecorate();
    }

    @Test
    public void shouldCallMethodLogClassStateForInputParameterIsBoolean() throws Exception {
        Logger logger = new Logger(managedState);
        whenConditionForUnbufferedInputParameters(PRIMITIVE_PREFIX, "primitive: true", "true");
        whenConditionForUnbufferedInputParameters(PRIMITIVE_PREFIX, "primitive: false", "false");

        logger.log(true);
        logger.log(false);

        verify(defaultState, times(1)).log("primitive: true");
        verify(defaultState, times(1)).log("primitive: false");
        verify(defaultState, times(2)).getDecorate();
    }

    @Test
    public void shouldCallMethodLogClassStateForInputParameterIsString() throws Exception {
        Logger logger = new Logger(managedState);
        when(managedState.getStringBufferState(any())).thenReturn(stringBufferState);

        logger.log("qqq");
        logger.log("qqq");
        logger.log("qqq");
        logger.log("qqq");

        verify(stringBufferState, times(4)).log("qqq");
    }

    @Test
    public void shouldCallMethodLogClassStateForInputParameterIsVarargsInts() throws Exception {
        Logger logger = new Logger(managedState);
        whenConditionForUnbufferedInputParameters(NOT_PREFIX, "15", "15");

        logger.log(1, 2, 3, 4, 5);

        verify(defaultState, times(1)).log("15");
        verify(defaultState, times(1)).getDecorate();
    }


    @Test
    public void shouldNeverCallMethodFlushWhenCalledMethodCloseWithoutPreliminaryLogging() throws Exception {
        Logger logger = new Logger(managedState);

        logger.close();

        verify(managedState, times(0)).getIntBufferState(any());
        verify(managedState, times(0)).getStringBufferState(any());
        verify(managedState, times(0)).getUnbufferedState(any());
    }

    @Test
    public void shouldCallMethodFlushOnceWhenCalledMethodCloseAfterLogging() throws Exception {
        Logger logger = new Logger(managedState);
        when(managedState.getIntBufferState(any())).thenReturn(intBufferState);
        logger.log(1);

        logger.close();

        verify(intBufferState, times(1)).flush();
    }

    private void whenConditionForUnbufferedInputParameters(String prefix, String result, String... args) throws Exception {
        when(managedState.getUnbufferedState(any())).thenReturn(defaultState);
        when(defaultState.getDecorate()).thenReturn(decorate);
        when(decorate.getDecorateString(prefix, args)).thenReturn(result);
    }


}

package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.IntBufferState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StateTest {

    private static final String PRIMITIVE_PREFIX = "primitive: %s";
    private static final String STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX = "string: %s (x%s)";
    private static final String STRING_PREFIX = "string: %s";

    private Printable printable;
    private Decorate decorate;

    @Before
    public void setUp() {
        printable = mock(Printable.class);
        decorate = mock(Decorate.class);
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOneResultForIntBuffer() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(6)))
                .thenReturn("primitive: " + 6);

        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.flush();

        verify(printable, times(1)).print("primitive: 6");
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOnceResultForStringBuffer() throws Exception {
        State state = new StringBufferState(printable, decorate);
        when(decorate.getDecorateString(STRING_WITH_NUMBER_OF_REPETITIONS_PREFIX, "str2", String.valueOf(3)))
                .thenReturn("string: str2 (x3)");

        state.log("str2");
        state.log("str2");
        state.log("str2");
        state.flush();

        verify(printable, times(1)).print("string: str2 (x3)");
    }

    @Test
    public void shouldNotCollMethodPrintForNumber() throws Exception {
        State state = new IntBufferState(printable, decorate);

        state.flush();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldNotCollMethodPrintForString() throws Exception {
        State state = new StringBufferState(printable, decorate);

        state.flush();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldCollMethodPrintForIntMaxValueMessageTwice() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(Integer.MAX_VALUE)))
                .thenReturn("primitive: " + Integer.MAX_VALUE);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(10)))
                .thenReturn("primitive: " + 10);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));
        state.flush();

        verify(printable, times(1)).print("primitive: " + Integer.MAX_VALUE);
        verify(printable, times(1)).print("primitive: 10");
    }

    @Test
    public void shouldCollMethodPrintForNumberWhenIntBufferIsZero() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(0)))
                .thenReturn("primitive: " + 0);

        state.log(String.valueOf(0));
        state.flush();

        verify(printable, times(1)).print("primitive: 0");
    }

    @Test
    public void shouldCollMethodPrintWhenMessageIsIntegerMaxValueTwice() throws Exception {
        State state = new StringBufferState(printable, decorate);
        when(decorate.getDecorateString(STRING_PREFIX, String.valueOf(Integer.MAX_VALUE)))
                .thenReturn("string: " + Integer.MAX_VALUE);
        when(decorate.getDecorateString(STRING_PREFIX, String.valueOf(10)))
                .thenReturn("string: " + 10);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));
        state.flush();

        verify(printable, times(1)).print("string: 10");
        verify(printable, times(1)).print("string: " + Integer.MAX_VALUE);
    }

    @Test
    public void shouldCollMethodPrintWhenMessageIsIntegerMinValueTwice() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(-30)))
                .thenReturn("primitive: " + -30);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(Integer.MIN_VALUE)))
                .thenReturn("primitive: " + Integer.MIN_VALUE);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(-100520)))
                .thenReturn("primitive: " + -100520);

        state.log("-10");
        state.log("-20");
        state.log(String.valueOf(Integer.MIN_VALUE));
        state.log("-20");
        state.log("-100500");
        state.flush();

        verify(printable, times(1)).print("primitive: " + String.valueOf(-30));
        verify(printable, times(1)).print("primitive: " + Integer.MIN_VALUE);
        verify(printable, times(1)).print("primitive: " + String.valueOf(-100520));
    }

    @Test
    public void shouldCollMethodPrintWhenIntegerMessageOverflowPositive() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(Integer.MAX_VALUE - 2)))
                .thenReturn("primitive: " + (Integer.MAX_VALUE - 2));

        state.log(String.valueOf(Integer.MAX_VALUE - 1));
        state.log(String.valueOf(-1));
        state.log(String.valueOf(10));
        state.flush();

        verify(printable, times(1)).print("primitive: " + (Integer.MAX_VALUE - 2));
    }

    @Test
    public void shouldCollMethodPrintWhenIntegerMessageOverflowNegative() throws Exception {
        State state = new IntBufferState(printable, decorate);
        when(decorate.getDecorateString(PRIMITIVE_PREFIX, String.valueOf(Integer.MIN_VALUE + 2)))
                .thenReturn("primitive: " + (Integer.MIN_VALUE + 2));

        state.log(String.valueOf(Integer.MIN_VALUE + 1));
        state.log(String.valueOf(1));
        state.log(String.valueOf(-10));
        state.flush();

        verify(printable, times(1)).print("primitive: " + (Integer.MIN_VALUE + 2));
    }
}

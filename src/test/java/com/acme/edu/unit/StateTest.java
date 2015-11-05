package com.acme.edu.unit;

import com.acme.edu.Decorate;
import com.acme.edu.exceptions.LogWritingException;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.IntBufferState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringBufferState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StateTest {

    private Printable printable;
    private Decorate decorate = String::format;

    @Before
    public void setUp() {
        printable = mock(Printable.class);
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOneResultForIntBuffer() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.flush();

        verify(printable, times(1)).print("primitive: 6");
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOnceResultForStringBuffer() throws LogWritingException {
        State state = new StringBufferState(printable, decorate);

        state.log("str2");
        state.log("str2");
        state.log("str2");
        state.flush();

        verify(printable, times(1)).print("string: str2 (x3)");
    }

    @Test
    public void shouldNotCollMethodPrintForNumber() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.flush();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldNotCollMethodPrintForString() throws LogWritingException {
        State state = new StringBufferState(printable, decorate);

        state.flush();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldCollMethodPrintForIntMaxValueMessageTwice() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));
        state.flush();

        verify(printable, times(1)).print("primitive: " + Integer.MAX_VALUE);
        verify(printable, times(1)).print("primitive: 10");
    }

    @Test
    public void shouldCollMethodPrintForNumberWhenIntBufferIsZero() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.log(String.valueOf(0));
        state.flush();

        verify(printable, times(1)).print("primitive: 0");
    }

    @Test
    public void shouldCollMethodPrintWhenMessageIsIntegerMaxValueTwice() throws LogWritingException {
        State state = new StringBufferState(printable, decorate);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));
        state.flush();

        verify(printable, times(1)).print("string: 10");
        verify(printable, times(1)).print("string: " + Integer.MAX_VALUE);
    }

    @Test
    public void shouldCollMethodPrintWhenMessageIsIntegerMinValueTwice() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

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
    public void shouldCollMethodPrintWhenIntegerMessageOverflowPositive() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.log(String.valueOf(Integer.MAX_VALUE - 1));
        state.log(String.valueOf(-1));
        state.log(String.valueOf(10));
        state.flush();

        verify(printable, times(1)).print("primitive: " + (Integer.MAX_VALUE - 2));
    }

    @Test
    public void shouldCollMethodPrintWhenIntegerMessageOverflowNegative() throws LogWritingException {
        State state = new IntBufferState(printable, decorate);

        state.log(String.valueOf(Integer.MIN_VALUE + 1));
        state.log(String.valueOf(1));
        state.log(String.valueOf(-10));
        state.flush();

        verify(printable, times(1)).print("primitive: " + (Integer.MIN_VALUE + 2));
    }
}

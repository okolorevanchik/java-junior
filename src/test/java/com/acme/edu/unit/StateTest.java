package com.acme.edu.unit;

import com.acme.edu.Printable;
import com.acme.edu.states.NumberState;
import com.acme.edu.states.State;
import com.acme.edu.states.StringState;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StateTest {

    private Printable printable;

    @Before
    public void setUp() {
        printable = mock(Printable.class);
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOneResultForIntBuffer() {
        State state = new NumberState(printable);

        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.log(String.valueOf(2));
        state.displayBuffer();

        verify(printable, times(1)).print("primitive: 6");
    }

    @Test
    public void shouldCollMethodPrintOnceAndPrintOnceResultForStringBuffer() {
        State state = new StringState(printable);

        state.log("str2");
        state.log("str2");
        state.log("str2");
        state.displayBuffer();

        verify(printable, times(1)).print("string: str2 (x3)");
    }

    @Test
    public void shouldNotCollMethodPrintForNumber() {
        State state = new NumberState(printable);

        state.displayBuffer();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldNotCollMethodPrintForString() {
        State state = new StringState(printable);

        state.displayBuffer();

        verify(printable, times(0)).print("");
    }

    @Test
    public void shouldCollMethodPrintForIntMaxValueMessageTwice() {
        State state = new NumberState(printable);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));

        verify(printable, times(1)).print("primitive: " + Integer.MAX_VALUE);
        verify(printable, times(1)).print("primitive: 10");
    }

    @Test
    public void shouldCollMethodPrintForNumberWhenIntBufferIsZero() {
        State state = new NumberState(printable);

        state.log(String.valueOf(0));
        state.displayBuffer();

        verify(printable, times(1)).print("primitive: 0");
    }

    @Test
    public void shouldCollMethodPrintForNumberWhenIntBufferIsNotZero() {
        State state = new NumberState(printable);

        state.log(String.valueOf(10));
        state.log(String.valueOf(0));
        state.displayBuffer();

        verify(printable, times(1)).print("primitive: 0");
        verify(printable, times(1)).print("primitive: 10");
    }

    @Test
    public void shouldCollMethodPrintWhenMessageIsIntegerMaxValueTwice() {
        State state = new StringState(printable);

        state.log("10");
        state.log(String.valueOf(Integer.MAX_VALUE));
        state.displayBuffer();

        verify(printable, times(1)).print("string: 10");
        verify(printable, times(1)).print("string: " + Integer.MAX_VALUE);
    }
}

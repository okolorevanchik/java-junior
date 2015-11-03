package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.Printable;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoggerTest {

    private Logger logger;
    private Printable printable;

    @Before
    public void setUp() {
        printable = mock(Printable.class);
        logger = new Logger(printable);
    }

    @Test
    public void shouldCollMethodPrintForIntMessageOnce() {
        logger.log(10);
        logger.log(10);
        logger.close();

        verify(printable, times(1)).print("primitive: 20");
    }

    @Test
    public void shouldCollMethodPrintForIntMaxValueMessageTwice() {
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        verify(printable, times(2));
    }

    @Test
    public void shouldCollLogForChar() {
        logger.log('q');
        verify(printable, times(1)).print("char: q");
    }
}

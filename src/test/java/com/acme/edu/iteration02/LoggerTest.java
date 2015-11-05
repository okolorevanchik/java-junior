package com.acme.edu.iteration02;

import com.acme.edu.Decorate;
import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.IncorrectInputsParametersMethodException;
import com.acme.edu.exceptions.LogWritingException;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.ManagedState;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();
    private static final Printable PRINTABLE = new ConsolePrinter();
    private static final Decorate DECORATE = String::format;

    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws IOException, IncorrectArgumentsConstructorException {
        resetOut();
        captureSysout();
        logger = new Logger(new ManagedState(PRINTABLE, DECORATE));
    }
    //endregion

    @After
    public void setUpResetOut() throws IOException {
        resetOut();
    }

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException, LogWritingException, IncorrectInputsParametersMethodException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "primitive: 3" + SEP +
                        "string: str 2" + SEP +
                        "primitive: 0" + SEP
        );
        //endregion
    }


    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws LogWritingException, IncorrectInputsParametersMethodException {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "primitive: 10" + SEP +
                        "primitive: " + Integer.MAX_VALUE + SEP +
                        "string: str 2" + SEP +
                        "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException, IncorrectInputsParametersMethodException, LogWritingException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "string: str 2 (x2)" + SEP +
                        "primitive: 0" + SEP +
                        "string: str 2" + SEP +
                        "string: str 3 (x3)" + SEP
        );
        //endregion
    }

}
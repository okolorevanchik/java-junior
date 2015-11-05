package com.acme.edu.iteration01;

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
    public void shouldLogInteger() throws IOException, LogWritingException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0" + SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException, LogWritingException {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.log((byte) -1);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: 0");
        //endregion
    }

    @Test
    public void shouldLogChar() throws IOException, IncorrectInputsParametersMethodException, LogWritingException {
        //region when
        logger.log('a');
        logger.log('b');
        logger.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException, IncorrectInputsParametersMethodException, LogWritingException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException, IncorrectInputsParametersMethodException, LogWritingException {
        //region when
        logger.log(true);
        logger.log(false);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException, IncorrectInputsParametersMethodException, LogWritingException {
        //region when
        logger.log(new Object());
        logger.close();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}
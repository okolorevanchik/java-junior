package com.acme.edu.iteration03;

import com.acme.edu.Decorate;
import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.printers.Printable;
import com.acme.edu.states.ManagedState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    private static final String SEP = System.lineSeparator();
    private static final Printable PRINTABLE = new ConsolePrinter();
    private static final Decorate DECORATE = String::format;

    private Logger logger;
    
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
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
    public void shouldLogIntegersArray() throws IOException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {" + SEP +
                        "{-1, 0, 1}" + SEP +
                        "{1, 2, 3}" + SEP +
                        "{-1, -2, -3}" + SEP +
                        "}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        logger.log(new int[][][][]{{{{0}}}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {" + SEP +
                        "{" + SEP + "{" + SEP +
                        "{" + "0" + "}" + SEP +
                        "}" + SEP + "}" + SEP +
                        "}" + SEP
        );
        //endregion
    }


    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when

        // Исправь!!!!!!!111111111111
        logger.log("str1", "string 2", "str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("str1" + SEP + "string 2" + SEP + "str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: " + 1);
        assertSysoutContains("string: str");
        assertSysoutContains("primitive: " + (Integer.MAX_VALUE - 10));
        assertSysoutContains("primitive: " + 11);
        //endregion
    }
}
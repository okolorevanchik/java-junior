package com.acme.edu;

/**
 * Logger class provides functions for logging data.
 * For correct processing and the withdrawal of all
 * accumulated data, before the end of the work to
 * the class must call {@link #close()}.
 * <p>
 * Example:
 * <p>
 * <code>public void someMethod() {
 * Logger.log("str 1");
 * Logger.log(1);
 * Logger.log(2);
 * Logger.log("str 2");
 * Logger.log(0);
 * Logger.close();
 * }</code>
 *
 * @autor Tolchinskiy Dmitriy
 */
public class Logger {

    private State currentState;
    private Printer printer;

    public Logger(Printer printer) {
        this.printer = printer;
        this.currentState = new StringState(printer);
    }

    /**
     * Method triggers summing the numbers applied
     * to the input and output in the console.
     *
     * @param message The int to be summing or printed.
     */
    public void log(int message) {
        if (currentState.getStateEnum() == StateEnum.STRING) {
            currentState.displayBuffer();
            currentState = new NumberState(printer);
        }
        currentState.log(message);
    }

    /**
     * Displays character in the console.
     *
     * @param message The char to be printed.
     */
    public void log(char message) {
        currentState.log(message);
    }

    /**
     * Displays boolean value in the console.
     *
     * @param message The boolean to be printed.
     */
    public void log(boolean message) {
        currentState.log(message);
    }

    /**
     * Displays string in the console.
     *
     * @param message The string to be printed.
     */
    public void log(String message) {
        if (currentState.getStateEnum() == StateEnum.NUMBER) {
            currentState.displayBuffer();
            currentState = new StringState(printer);
        }
        currentState.log(message);
    }

    /**
     * Displays string representation of integers.
     *
     * @param messages The array integers to be printed.
     */
    public void log(int... messages) {
        currentState.log(messages);
    }

    /**
     * Displays matrix representation of integers.
     *
     * @param matrixMessages The matrix to be printed.
     */
    public void log(int[][] matrixMessages) {
        currentState.log(matrixMessages);
    }

    /**
     * Displays multimatrix representation of integers.
     *
     * @param multimatrixMessages The multimatrix to be printed.
     */
    public void log(int[][][][] multimatrixMessages) {
        currentState.log(multimatrixMessages);
    }

    /**
     * It displays the console strings.
     *
     * @param messages The strings to be printed.
     */
    public void log(String... messages) {
        currentState.log(messages);
    }

    /**
     * Prints an result toString() method Object class in console.
     *
     * @param message The object reference to be printed.
     */
    public void log(Object message) {
        currentState.log(message);
    }

    /**
     * Displays the residual data to the console.
     * Called before the cessation of work with logger.
     */
    public void close() {
        currentState.displayBuffer();
    }
}

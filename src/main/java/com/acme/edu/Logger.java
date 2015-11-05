package com.acme.edu;

import com.acme.edu.exceptions.IncorrectArgumentsConstructorException;
import com.acme.edu.exceptions.IncorrectInputsParametersMethodException;
import com.acme.edu.exceptions.SendingDataOverNetworkException;
import com.acme.edu.exceptions.WritingDataToFileException;
import com.acme.edu.states.ManagedState;
import com.acme.edu.states.State;

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
 * @author Tolchinskiy Dmitriy
 */
public class Logger {

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
    private State currentState;

    public Logger(ManagedState managedState) {
        if (managedState == null) {
            throw new IncorrectArgumentsConstructorException();
        }
        this.managedState = managedState;
    }

    /**
     * Method triggers summing the numbers applied
     * to the input and output in the console.
     *
     * @param message The int to be summing or printed.
     */
    public void log(int message) throws WritingDataToFileException, SendingDataOverNetworkException {
        currentState = managedState.getNumberState(currentState);
        currentState.log(String.valueOf(message));

    }

    /**
     * Displays character in the console.
     *
     * @param message The char to be printed.
     */
    public void log(char message) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(CHAR_PREFIX, String.valueOf(message));
    }

    /**
     * Displays boolean value in the console.
     *
     * @param message The boolean to be printed.
     */
    public void log(boolean message) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(PRIMITIVE_PREFIX, String.valueOf(message));
    }

    /**
     * Displays string in the console.
     *
     * @param message The string to be printed.
     */
    public void log(String message) throws WritingDataToFileException, SendingDataOverNetworkException {
        checkNullObjectOrEmptyString(message);
        currentState = managedState.getStringState(currentState);
        currentState.log(message);

    }

    /**
     * Displays string representation of integers.
     *
     * @param messages The array integers to be printed.
     */
    public void log(int... messages) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(NOT_PREFIX, getSumOfNumbersInArray(messages));
    }

    /**
     * Displays matrix representation of integers.
     *
     * @param matrixMessages The matrix to be printed.
     */
    public void log(int[][] matrixMessages) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(PRIMITIVES_MATRIX_PREFIX, printMatrix(matrixMessages));
    }

    /**
     * Displays multimatrix representation of integers.
     *
     * @param multimatrixMessages The multimatrix to be printed.
     */
    public void log(int[][][][] multimatrixMessages) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(PRIMITIVES_MULTIMATRIX_PREFIX, printMultimatrix(multimatrixMessages));
    }

    /**
     * It displays the console strings.
     *
     * @param messages The strings to be printed.
     */
    public void log(String... messages) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(NOT_PREFIX, arrayStringToString(messages));
    }

    /**
     * Prints an result toString() method Object class in console.
     *
     * @param message The object reference to be printed.
     */
    public void log(Object message) throws WritingDataToFileException, SendingDataOverNetworkException {
        printDefaultMessage(REFERENCE_PREFIX, message.toString());
    }

    /**
     * Displays the residual data to the console.
     * Called before the cessation of work with logger.
     */
    public void close() throws WritingDataToFileException, SendingDataOverNetworkException {
        currentState.flush();
    }

    private void printDefaultMessage(String prefix, String message) throws WritingDataToFileException, SendingDataOverNetworkException {
        checkNullObjectOrEmptyString(message);
        currentState = managedState.getDefaultState(currentState);
        String result = currentState.getDecorate().getDecorateString(prefix, message);
        currentState.log(result);

    }

    private void checkNullObjectOrEmptyString(Object message) {
        if (message == null || message.toString().isEmpty()) {
            throw new IncorrectInputsParametersMethodException();
        }
    }

    private String getSumOfNumbersInArray(int[] messages) {
        checkNullObjectOrEmptyString(messages);

        int sumOfNumbersInArray = 0;
        for (int message : messages) {
            sumOfNumbersInArray += message;
        }

        return String.valueOf(sumOfNumbersInArray);
    }

    private String printMatrix(int[][] arrayMessages) {
        checkNullObjectOrEmptyString(arrayMessages);

        StringBuilder result = new StringBuilder();
        for (int[] arrayMessage : arrayMessages) {
            checkNullObjectOrEmptyString(arrayMessages);
            result.append(OPEN_BRACKET);
            for (int message : arrayMessage) {
                result.append(message).append(", ");
            }
            result.replace(result.length() - 2, result.length(), CLOSE_BRACKET + SEP);
        }

        return result.toString();
    }

    private String printMultimatrix(int[][][][] multimatrixMessages) {
        checkNullObjectOrEmptyString(multimatrixMessages);

        StringBuilder result = new StringBuilder(OPEN_BRACKET + SEP);
        for (int[][][] multimatrix : multimatrixMessages) {
            checkNullObjectOrEmptyString(multimatrix);
            result.append(OPEN_BRACKET).append(SEP);
            for (int[][] matrix : multimatrix) {
                result.append(printMatrix(matrix));
            }
            result.append(CLOSE_BRACKET).append(SEP);
        }
        result.append(CLOSE_BRACKET).append(SEP);

        return result.toString();
    }

    private String arrayStringToString(String... messages) {
        checkNullObjectOrEmptyString(messages);

        StringBuilder result = new StringBuilder();
        for (String message : messages) {
            checkNullObjectOrEmptyString(message);
            result.append(message).append(SEP);
        }

        return result.toString();
    }
}

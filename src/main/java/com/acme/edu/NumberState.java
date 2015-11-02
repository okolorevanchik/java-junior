package com.acme.edu;

public class NumberState extends State {

    private static final StateEnum state = StateEnum.NUMBER;

    private int buffer = 0;

    public NumberState(Printer printer) {
        super(printer);
    }

    @Override
    public void displayBuffer() {
        if (buffer == 0) {
            return;
        }
        getPrinter().print(PRIMITIVE_PREFIX + buffer);
        buffer = 0;
    }

    @Override
    protected void magic(String message) {
        int intMessage = Integer.parseInt(message);
        if (intMessage != 0 && intMessage < Integer.MAX_VALUE) {
            summing(intMessage);
        } else if (intMessage == Integer.MAX_VALUE) {
            getPrinter().print(PRIMITIVE_PREFIX + buffer);
            getPrinter().print(PRIMITIVE_PREFIX + Integer.MAX_VALUE);
            buffer = 0;
        } else if (buffer == 0) {
            getPrinter().print(PRIMITIVE_PREFIX + 0);
        } else {
            getPrinter().print(PRIMITIVE_PREFIX + message);
        }
    }

    @Override
    protected StateEnum getStateEnum() {
        return state;
    }

    private void summing(int message) {
        int temp = buffer;
        buffer += message;
        if (buffer < temp && message > 0) {
            getPrinter().print(PRIMITIVE_PREFIX + temp);
            buffer = message;
        }
    }
}

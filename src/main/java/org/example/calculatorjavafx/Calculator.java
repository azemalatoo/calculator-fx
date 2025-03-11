package org.example.calculatorjavafx;

/**
 * A simple calculator class that performs basic arithmetic operations.
 */
public class Calculator {
    private double operand1;
    private double operand2;
    private char operator;
    private double result;
    private boolean isError;
    private static final char NO_OPERATOR = '\0';

    public Calculator() {
        reset();
    }

    public void setOperand1(double operand) {
        this.operand1 = operand;
    }

    public void setOperand2(double operand) {
        this.operand2 = operand;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    /**
     * Performs the calculation based on the operands and operator.
     * Sets isError to true if an error occurs (e.g., division by zero, invalid operator).
     */
    public void calculate() {
        isError = false;
        try {
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 == 0) {
                        isError = true; // Division by zero
                    } else {
                        result = operand1 / operand2;
                    }
                    break;
                default:
                    isError = true; // Invalid operator
            }
        } catch (ArithmeticException e) {
            isError = true; // Other arithmetic error
        }
    }


    public double getResult() {
        return result;
    }


    public boolean isError() {
        return isError;
    }

    /**
     * Resets the calculator to its initial state.
     */
    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = NO_OPERATOR;
        result = 0;
        isError = false;
    }
}
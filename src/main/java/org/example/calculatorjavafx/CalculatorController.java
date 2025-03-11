package org.example.calculatorjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class for the calculator application.
 * Handles user interactions and updates the UI.
 */
public class CalculatorController {

    @FXML
    private TextField displayField;

    private Calculator calculator = new Calculator();
    private boolean isOperand1Set = false;
    private boolean decimalAdded = false;

    /**
     * Handles digit button clicks.
     * Appends the digit to the display field.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void handleDigit(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        displayField.appendText(button.getText());
    }

    /**
     * Handles operator button clicks.
     * Parses the current display value as the first operand, sets the operator,
     * clears the display, and prepares for the second operand.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void handleOperator(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String currentText = displayField.getText();

        if (!currentText.isEmpty()) {
            try {
                double value = Double.parseDouble(currentText);
                calculator.setOperand1(value);
                calculator.setOperator(button.getText().charAt(0));
                isOperand1Set = true;
                displayField.clear();
                decimalAdded = false;
            } catch (NumberFormatException e) {
                displayField.setText("Error: Invalid Input");
                calculator.reset();
                isOperand1Set = false;
                decimalAdded = false;
            }
        }
    }

    /**
     * Handles the equals button click.
     * Parses the current display value as the second operand, performs the calculation,
     * and displays the result or an error message.
     */
    @FXML
    public void handleEquals() {
        String currentText = displayField.getText();
        if (!currentText.isEmpty() && isOperand1Set) {
            try {
                double value = Double.parseDouble(currentText);
                calculator.setOperand2(value);
                calculator.calculate();
                if (calculator.isError()) {
                    displayField.setText("Error: Division by Zero");
                } else {
                    displayField.setText(String.valueOf(calculator.getResult()));
                }
                calculator.reset();
                isOperand1Set = false;
                decimalAdded = false;
            } catch (NumberFormatException e) {
                displayField.setText("Error: Invalid Input");
                calculator.reset();
                isOperand1Set = false;
                decimalAdded = false;
            }
        }
    }

    /**
     * Handles the clear button click.
     * Clears the display and resets the calculator state.
     */
    @FXML
    public void handleClear() {
        displayField.clear();
        calculator.reset();
        isOperand1Set = false;
        decimalAdded = false;
    }

    /**
     * Handles the decimal button click.
     * Adds a decimal point to the display if one hasn't already been added.
     * If the display is empty it adds "0."
     */
    @FXML
    public void handleDecimal() {
        if (!decimalAdded && !displayField.getText().contains(".")) {
            if (displayField.getText().isEmpty()) {
                displayField.appendText("0.");
            } else {
                displayField.appendText(".");
            }
            decimalAdded = true;
        }
    }
}
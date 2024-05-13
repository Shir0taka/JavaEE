package com.books.bookscrud.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@RequestScoped
public class CalculationBean implements Serializable {

    @Inject
    private int value;
    private String operator;
    private int operand;
    private int result;

    public void calculate() {
        switch (operator) {
            case "+":
                result = value + operand;
                break;
            case "-":
                result = value  - operand;
                break;
            case "*":
                result = value * operand;
                break;
            case "/":
                if (operand != 0) {
                    result = value / operand;
                }
                break;
            default:
                result = 0;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int operand1) {
        this.value = operand1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand2) {
        this.operand = operand2;
    }

    public int getResult() {
        return result;
    }
}
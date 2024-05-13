package com.books.bookscrud.controllers;

import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class CalculationController implements Serializable {

    private int value;

    public CalculationController() {
        this.value = 0;
    }

    @Produces
    public int produceIntValue() {
        return value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int number) {
        value += number;
    }

    public void subtract(int number) {
        value -= number;
    }
}
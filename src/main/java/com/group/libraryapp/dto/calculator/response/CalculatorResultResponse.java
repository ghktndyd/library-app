package com.group.libraryapp.dto.calculator.response;

import lombok.Getter;

//@Getter
public class CalculatorResultResponse {

    private final int add;
    private final int minus;
    private final int multiply;

    private int add(int num1, int num2) {
        return num1 + num2;
    }

    private int minus(int num1, int num2) {
        return num1 - num2;
    }

    private int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public CalculatorResultResponse(int num1, int num2) {
        this.add = add(num1, num2);
        this.minus = minus(num1, num2);
        this.multiply = multiply(num1, num2);
    }
}

package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorRequest;
import com.group.libraryapp.dto.calculator.response.CalculatorResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorRequest addRequest) {
        return addRequest.getNumber1() + addRequest.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public CalculatorResultResponse calculateTwoNumbers(CalculatorRequest calculatorRequest) {

        int num1 = calculatorRequest.getNumber1();
        int num2 = calculatorRequest.getNumber2();

        return new CalculatorResultResponse(num1, num2);
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorRequest request) {

        return request.getNumber1() * request.getNumber2();
    }


}

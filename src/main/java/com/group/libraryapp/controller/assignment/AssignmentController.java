package com.group.libraryapp.controller.assignment;

import com.group.libraryapp.dto.assignment.request.DayOfTheWeekRequest;
import com.group.libraryapp.dto.assignment.request.SumRequest;
import com.group.libraryapp.dto.assignment.response.DayOfTheWeekResponse;
import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController {

    @GetMapping("/api/v1/day-of-the-week")
    public DayOfTheWeekResponse getDayOfTheWeek(@Validated DayOfTheWeekRequest request) {
        return new DayOfTheWeekResponse(request.getDate());
    }

    @PostMapping("/api/v1/sum")
    public int sum(@RequestBody SumRequest request) {
        int sum = 0;

        List<Integer> numbers = request.getNumbers();

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }

    @GetMapping("/add22")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }
}

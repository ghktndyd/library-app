package com.group.libraryapp.dto.assignment.response;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
public class DayOfTheWeekResponse {

    private final String dayOfTheWeek;

    public DayOfTheWeekResponse(LocalDate dayOfTheWeek) {
        this.dayOfTheWeek = convertDateToDayOfWeek(dayOfTheWeek);
    }

    private String convertDateToDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return dayOfWeek.toString();
    }
}

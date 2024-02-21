package com.group.libraryapp.dto.assignment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DayOfTheWeekRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}

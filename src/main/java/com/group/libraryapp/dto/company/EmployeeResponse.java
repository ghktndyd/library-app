package com.group.libraryapp.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String name;
    private String teamName;
    private String role;
    private LocalDate birthDay;
    private LocalDate entryDate;
}

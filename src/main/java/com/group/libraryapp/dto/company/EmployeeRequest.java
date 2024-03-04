package com.group.libraryapp.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeRequest {

    @NotBlank(message = "직원 이름은 필수 값입니다.")
    private String name;

    private boolean isManager;

    @PastOrPresent // now 또는 과거
    @NotNull(message = "입사일은 필수 값입니다.")
    private LocalDate entryDate;

    @Past // 과거
    private LocalDate birthDay;

    private Long teamId;
}
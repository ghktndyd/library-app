package com.group.libraryapp.dto.assignment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SumRequest {
    private List<Integer> numbers;
}

package com.group.libraryapp.dto.fruit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class FruitRequest {
    private String name;
    private LocalDate warehousingDate;
    private Long price;
    private int quantity;
    private int enterQuantity;
}

package com.group.libraryapp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Fruit {
    private Long id;
    private String name;
    private LocalDate warehousingDate;
    private int price;
    private int enterQuantity;
    private int quantity;
}

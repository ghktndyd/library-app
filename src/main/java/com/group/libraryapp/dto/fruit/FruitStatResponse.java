package com.group.libraryapp.dto.fruit;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FruitStatResponse {
    private String name;
    private int saleAmount;
    private int notSaleAmount;

}

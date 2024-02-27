package com.group.libraryapp.dto.fruit;

import com.group.libraryapp.domain.user.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FruitOptionResponse {
    private String name;
    private int price;
    private int quantity;

    public FruitOptionResponse(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.quantity = fruit.getQuantity();
    }
}


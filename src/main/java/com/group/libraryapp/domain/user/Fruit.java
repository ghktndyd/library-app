package com.group.libraryapp.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate warehousingDate;
    private int price;
    private int quantity;
    private int enterQuantity;

    public Fruit(String name, LocalDate warehousingDate, int price, int quantity, int enterQuantity) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
        this.quantity = quantity;
        this.enterQuantity = enterQuantity;
    }
}

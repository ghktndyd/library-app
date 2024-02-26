package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.user.Fruit;

import java.util.Optional;

public interface FruitRepository {
    void save(Fruit fruit);
    Optional<Fruit> findById(Long id);
    int updateQuantity(Long id, int count);
    Integer findSoldAmountByName(String name);
    Integer findUnsoldAmountByName(String name);
}

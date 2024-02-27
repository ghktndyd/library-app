package com.group.libraryapp.repository.fruit.impl;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
// @Primary
public class FruitMemoryRepository implements FruitRepository {

    private final Map<Long, Fruit> storage = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public void save(Fruit fruit) {
        Long id = fruit.getId() == null ? sequence.incrementAndGet() : fruit.getId();
        fruit.setId(id);
        storage.put(id, fruit);
    }

    @Override
    public Optional<Fruit> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public int updateQuantity(Long id, int count) {
        Fruit fruit = storage.get(id);
        if (fruit == null) {
            return 0;
        }
        fruit.setQuantity(fruit.getQuantity() - count);
        return 1;
    }

    @Override
    public Integer findSoldAmountByName(String name) {
        return storage.values().stream()
                .filter(fruit -> name.equals(fruit.getName()))
                .mapToInt(fruit -> (fruit.getEnterQuantity() - fruit.getQuantity()) * fruit.getPrice())
                .sum();
    }

    @Override
    public Integer findUnsoldAmountByName(String name) {
        return storage.values().stream()
                .filter(fruit -> name.equals(fruit.getName()))
                .mapToInt(fruit -> fruit.getQuantity() * fruit.getPrice())
                .sum();
    }
}

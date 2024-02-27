package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.dto.fruit.FruitOptionResponse;
import com.group.libraryapp.dto.fruit.FruitRequest;
import com.group.libraryapp.dto.fruit.FruitSaleRequest;
import com.group.libraryapp.dto.fruit.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public void addFruit(FruitRequest request) {
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice(), request.getQuantity(), request.getEnterQuantity());
        fruitRepository.save(fruit);
    }

    @Transactional
    public void saleFruit(FruitSaleRequest request) {
        Fruit fruit = fruitRepository.findById(request.getId()).orElseThrow(() -> new NoSuchElementException("해당 과일이 존재하지 않습니다."));
        if (fruit.getQuantity() < request.getCount()) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + fruit.getQuantity());
        }
        fruitRepository.updateQuantity(request.getId(), request.getCount());
    }

    public FruitStatResponse getFruitStat(String name) {
        Integer saleAmount = fruitRepository.findSoldAmountByName(name);
        Integer notSaleAmount = fruitRepository.findUnsoldAmountByName(name);
        return new FruitStatResponse(name, saleAmount, notSaleAmount);
    }

    public long countFruitsByName(String name) {
        return fruitRepository.countByName(name);
    }

    public List<FruitOptionResponse> findFruitsByPrice(String option, int price) {
        List<Fruit> fruits;
        if ("GTE".equals(option)) {
            fruits = fruitRepository.findByPriceGreaterThanEqualAndQuantityGreaterThan(price, 0);
        } else if ("LTE".equals(option)) {
            fruits = fruitRepository.findByPriceLessThanEqualAndQuantityGreaterThan(price, 0);
        } else {
            throw new IllegalArgumentException("Invalid option: " + option);
        }
        return fruits.stream()
                .map(FruitOptionResponse::new)
                .collect(Collectors.toList());
    }
}

package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.dto.fruit.FruitRequest;
import com.group.libraryapp.dto.fruit.FruitSaleRequest;
import com.group.libraryapp.dto.fruit.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import com.group.libraryapp.repository.fruit.impl.FruitRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class FruitService {

    private final FruitRepositoryImpl fruitRepository;

    public FruitService(FruitRepositoryImpl fruitRepository) {
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
}

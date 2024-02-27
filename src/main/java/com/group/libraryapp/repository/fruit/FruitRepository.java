package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.user.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    Optional<Fruit> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Fruit f SET f.quantity = f.quantity - :count WHERE f.id = :id")
    int updateQuantity(@Param("id") Long id, @Param("count") int count);

    Integer findSoldAmountByName(String name);

    Integer findUnsoldAmountByName(String name);

    long countByName(String name);

    List<Fruit> findByPriceGreaterThanEqualAndQuantityGreaterThan(int price, int quantity);

    List<Fruit> findByPriceLessThanEqualAndQuantityGreaterThan(int price, int quantity);
}

package com.group.libraryapp.repository.fruit.impl;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public class FruitRepositoryImpl implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Fruit fruit) {
        String sql = "INSERT INTO fruit (name, warehousing_date, price, quantity, enter_quantity) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice(), fruit.getQuantity(), fruit.getEnterQuantity());
    }

    @Override
    public Optional<Fruit> findById(Long id) {
        String sql = "SELECT * FROM fruit WHERE id = ?";
        Fruit fruit = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Fruit(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDate("warehousing_date").toLocalDate(),
                rs.getInt("price"),
                rs.getInt("quantity"),
                rs.getInt("enter_quantity")
        ));
        return Optional.ofNullable(fruit);
    }

    @Override
    public int updateQuantity(Long id, int count) {
        String sql = "UPDATE fruit SET quantity = quantity - ? WHERE id = ?";
        return jdbcTemplate.update(sql, count, id);
    }

    @Override
    public Integer findSoldAmountByName(String name) {
        String sql = "SELECT SUM((enter_quantity - quantity) * price) FROM fruit WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }

    @Override
    public Integer findUnsoldAmountByName(String name) {
        String sql = "SELECT SUM(quantity * price) FROM fruit WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }
}

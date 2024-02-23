package com.group.libraryapp.controller.assignment;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.dto.fruit.FruitRequest;
import com.group.libraryapp.dto.fruit.FruitSaleRequest;
import com.group.libraryapp.dto.fruit.FruitStatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/api/v1/fruit")
    public ResponseEntity<Void> addFruit(@RequestBody FruitRequest request) {
        String sql = "insert into fruit (name, warehousing_date, price, quantity, enter_quantity) values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice(), request.getQuantity(), request.getEnterQuantity());

        // 검증할 것
        // 1. 이름이 문자열 형태인가?
        // 2. 입고일자가 yyyy-MM-dd 형식인가?
        // 3. 가격과 수량이 숫자 형태인가?

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/v1/fruit")
    public ResponseEntity<Void> saleFruit(@RequestBody FruitSaleRequest request) {

        // 요청 DTO의 id가 테이블에 존재하는가?
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        Fruit fruit = jdbcTemplate.queryForObject(readSql, new Object[]{request.getId()}, (rs, rowNum) -> new Fruit(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDate("warehousing_date").toLocalDate(),
                rs.getInt("price"),
                rs.getInt("quantity"),
                rs.getInt("enter_quantity")
        ));

        if (fruit == null) {
            throw new IllegalArgumentException("해당 과일이 존재하지 않습니다.");
        }

        // 요청 DTO의 구매 수량이 테이블의 재고보다 많은지 확인
        if (fruit.getQuantity() < request.getCount()) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + fruit.getQuantity());
        }

        String sql = "UPDATE fruit SET quantity = quantity - ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getCount(), request.getId());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getFruitStat(@RequestParam String name) {
        // 얼마가 판매되었나?
        String soldSql = "SELECT SUM((enter_quantity - quantity) * price) FROM fruit WHERE name = ?";
        Integer saleAmount = jdbcTemplate.queryForObject(soldSql, new Object[]{name}, Integer.class);
        if (saleAmount == null) {
            saleAmount = 0;
        }

        // 안 팔린건?
        String unsoldSql = "SELECT SUM(quantity * price) FROM fruit WHERE name = ?";
        Integer notSaleAmount = jdbcTemplate.queryForObject(unsoldSql, new Object[]{name}, Integer.class);
        if (notSaleAmount == null) {
            notSaleAmount = 0;
        }

        return new FruitStatResponse(name, saleAmount, notSaleAmount);
    }

}

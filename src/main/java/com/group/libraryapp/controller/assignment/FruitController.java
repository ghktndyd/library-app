package com.group.libraryapp.controller.assignment;

import com.group.libraryapp.dto.fruit.FruitOptionResponse;
import com.group.libraryapp.dto.fruit.FruitRequest;
import com.group.libraryapp.dto.fruit.FruitSaleRequest;
import com.group.libraryapp.dto.fruit.FruitStatResponse;

import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public ResponseEntity<Void> addFruit(@RequestBody FruitRequest request) {
        fruitService.addFruit(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/v1/fruit")
    public ResponseEntity<Void> saleFruit(@RequestBody FruitSaleRequest request) {
        fruitService.saleFruit(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getFruitStat(@RequestParam String name) {
        return fruitService.getFruitStat(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public ResponseEntity<Map<String, Long>> countFruitByName(@RequestParam String name) {
        long count = fruitService.countFruitsByName(name);
        Map<String, Long> response = Collections.singletonMap("count", count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/fruit/list")
    public ResponseEntity<List<FruitOptionResponse>> listFruits(@RequestParam String option, @RequestParam int price) {
        List<FruitOptionResponse> fruits = fruitService.findFruitsByPrice(option, price);
        return ResponseEntity.ok(fruits);
    }

}

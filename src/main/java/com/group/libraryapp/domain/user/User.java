package com.group.libraryapp.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    private Integer age;

    public User(String name, Integer age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 입력되었습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }
}

package com.group.libraryapp.domain.user;

import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String name;
    private Integer age;

    public User(String name, Integer age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 입력되었습니다.", name));
        }
        this.name = name;
        this.age = age;
    }
}

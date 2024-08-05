package com.programmers.jdbcproject.domain;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String nickname;

    public User(String nickname) {
        this.nickname = nickname;
    }
}

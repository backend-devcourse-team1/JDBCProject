package com.programmers.jdbcproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int userId;
    private String nickname;

    public User(String changeNickname, int id) {
        this.nickname = changeNickname;
        this.userId = id;
    }
}

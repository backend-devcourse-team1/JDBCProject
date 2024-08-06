package com.programmers.jdbcproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int userId;
    private String nickname;

    @Override
    public String toString() {
        return "userId=[" + userId + ']' +
                ", nickname=[" + nickname + ']';
    }
}

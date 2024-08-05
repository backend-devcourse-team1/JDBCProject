package com.programmers.jdbcproject.domain;

import lombok.Data;

@Data
public class Review {
    private int reviewId;
    private int movieId;
    private int userId;
    private String content;
    private int rating;
}

package com.programmers.jdbcproject.domain;

import lombok.Data;

@Data
public class Review {
    private int reviewId;
    private int movieId;
    private int userId;
    private String content;
    private int rating;

    public Review(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
    public Review(int reviewId, int movieId, int userId, String content, int rating) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "movieId=[" + movieId + ']' +
                ", userId=[" + userId + ']' +
                ", content=[" + content + ']' +
                ", rating=[" + rating + ']';
    }
}

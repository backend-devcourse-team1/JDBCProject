package com.programmers.java.jdbcproject.domain;

import lombok.Data;

@Data
public class Movie {
    private int movieId;
    private String title;
    private int rating;
    private int audiences;
    private String genre;
    private String director;
    private String cast;
    private String synopsis;
    private String crew;
    private String trailer;
}

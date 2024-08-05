package com.programmers.jdbcproject.domain;

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


    public Movie(String title, int rating, int audiences, String genre, String director, String cast, String synopsis, String crew, String trailer) {
        this.title = title;
        this.rating = rating;
        this.audiences = audiences;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        this.crew = crew;
        this.trailer = trailer;
    }
}

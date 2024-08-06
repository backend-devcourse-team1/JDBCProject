package com.programmers.jdbcproject.service;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;
import com.programmers.jdbcproject.repository.MovieRepository;
import com.programmers.jdbcproject.repository.ReviewRepository;
import com.programmers.jdbcproject.repository.UserRepository;
import com.programmers.jdbcproject.service.base.MovieService;
import com.programmers.jdbcproject.service.base.ReviewService;
import com.programmers.jdbcproject.service.base.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class SelectService implements CrudService {

    private final Connection conn;
    private static final Scanner sc = new Scanner(System.in);

    public SelectService(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void function(int type) {
        MovieService movieService = new MovieService(new MovieRepository(conn));
        ReviewService reviewService = new ReviewService(new ReviewRepository(conn));
        UserService userService = new UserService(new UserRepository(conn));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Movie> movies;
        List<User> users;
        switch (type){
            case 1:
                movies = movieService.findAllMovies();
                movies.forEach(System.out::println);
                break;
            case 2:
                movies = movieService.findOrderByAudiences();
                movies.forEach(System.out::println);
                break;
            case 3:
                movies = movieService.findOrderByRating();
                movies.forEach(System.out::println);
                break;
            case 4:
                List<Review> reviews = reviewService.findReviewById(inputMovieId());
                reviews.forEach(System.out::println);
                break;
            case 5:
                users = userService.findAll();
                users.forEach(System.out::println);
                break;
            default:
                break;
        }
    }

    private int inputMovieId() {
        System.out.print("Please movie id :: ");
        int movieId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return movieId;
    }
}

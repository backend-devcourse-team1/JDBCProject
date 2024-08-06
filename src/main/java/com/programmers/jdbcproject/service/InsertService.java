package com.programmers.jdbcproject.service;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.repository.MovieRepository;
import com.programmers.jdbcproject.repository.ReviewRepository;
import com.programmers.jdbcproject.repository.UserRepository;
import com.programmers.jdbcproject.service.base.MovieService;
import com.programmers.jdbcproject.service.base.ReviewService;
import com.programmers.jdbcproject.service.base.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class InsertService implements CrudService {

    Connection conn;
    private static final Scanner sc = new Scanner(System.in);

    public InsertService(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void function(int type) {
        MovieService movieService = new MovieService(new MovieRepository(conn));
        ReviewService reviewService = new ReviewService(new ReviewRepository(conn));
        UserService userService = new UserService(new UserRepository(conn));
        switch (type){
            case 1:
                movieService.save(inputAddMovie());
                break;
            case 2:
                reviewService.save(inputMovieTitle(), inputNickname(), inputReview());
                break;
            case 3:
                userService.save(inputNickname());
                break;
            default:
                break;
        }
    }

    private Movie inputAddMovie() {
        System.out.println("Please enter the movie information in order");
        System.out.print("title :: ");
        String title = sc.nextLine();

        System.out.print("rating :: ");
        int rating = Integer.parseInt(sc.nextLine());

        System.out.print("audiences :: ");
        int audiences = Integer.parseInt(sc.nextLine());

        System.out.print("genre :: ");
        String genre = sc.nextLine();

        System.out.print("director :: ");
        String director = sc.nextLine();

        System.out.print("cast :: ");
        String cast = sc.nextLine();

        System.out.print("synopsis :: ");
        String synopsis = sc.nextLine();

        System.out.print("crew :: ");
        String crew = sc.nextLine();

        System.out.print("trailer :: ");
        String trailer = sc.nextLine();
        System.out.println();

        return new Movie(title,rating,audiences,genre,director,cast,synopsis,crew,trailer);
    }

    private String inputNickname() {
        System.out.print("Please enter your nickname :: ");
        String nickname = sc.nextLine();
        System.out.println();
        return nickname;
    }

    private String inputMovieTitle() {
        System.out.print("Please enter Movie Title :: ");
        String movieTitle = sc.nextLine();
        System.out.println();
        return movieTitle;
    }

    private Review inputReview() {
        System.out.println("Please write a review");
        System.out.print("Content :: ");
        String reviewId = sc.nextLine();
        System.out.println();

        System.out.println("Rating :: ");
        int reviewRating = Integer.parseInt(sc.nextLine());
        System.out.println();

        return new Review(reviewId, reviewRating);
    }
}

package com.programmers.jdbcproject.service;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.User;
import com.programmers.jdbcproject.repository.MovieRepository;
import com.programmers.jdbcproject.repository.ReviewRepository;
import com.programmers.jdbcproject.repository.UserRepository;
import com.programmers.jdbcproject.service.base.MovieService;
import com.programmers.jdbcproject.service.base.ReviewService;
import com.programmers.jdbcproject.service.base.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class UpdateService implements CrudService {
    private final Connection conn;
    private static final Scanner sc = new Scanner(System.in);

    public UpdateService(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void function(int type) {
        MovieService movieService = new MovieService(new MovieRepository(conn));
        ReviewService reviewService = new ReviewService(new ReviewRepository(conn));
        UserService userService = new UserService(new UserRepository(conn));

        switch (type) {
            case 1:
                movieService.update(inputMovieId(), inputAddMovie());
                break;
            case 2:
                reviewService.update(inputReviewId(), inputReviewContent());
                break;
            case 3:
                userService.update(new User(inputUserId(), inputNickname()));
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

    private Movie inputAddMovie() {
        System.out.println("Please enter the movie information in order");
        System.out.print("title :: ");
        String title = sc.nextLine();
        System.out.println();

        System.out.print("rating :: ");
        int rating = Integer.parseInt(sc.nextLine());
        System.out.println();

        System.out.print("audiences :: ");
        int audiences = Integer.parseInt(sc.nextLine());
        System.out.println();

        System.out.print("genre :: ");
        String genre = sc.nextLine();
        System.out.println();

        System.out.print("director :: ");
        String director = sc.nextLine();
        System.out.println();

        System.out.print("cast :: ");
        String cast = sc.nextLine();
        System.out.println();

        System.out.print("synopsis :: ");
        String synopsis = sc.nextLine();
        System.out.println();

        System.out.print("crew :: ");
        String crew = sc.nextLine();
        System.out.println();

        System.out.print("trailer :: ");
        String trailer = sc.nextLine();
        System.out.println();

        return new Movie(title,rating,audiences,genre,director,cast,synopsis,crew,trailer);
    }

    private int inputReviewId() {
        System.out.print("Please review id :: ");
        int reviewId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return reviewId;
    }

    private String inputReviewContent() {
        System.out.print("Please update Content :: ");
        String reviewContent = sc.nextLine();
        System.out.println();
        return reviewContent;
    }

    private String inputNickname() {
        System.out.print("Please nickname :: ");
        String nickname = sc.nextLine();
        System.out.println();
        return nickname;
    }

    private int inputUserId() {
        System.out.print("Please user id :: ");
        int userId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return userId;
    }
}

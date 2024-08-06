package com.programmers.jdbcproject.service;

import com.programmers.jdbcproject.repository.MovieRepository;
import com.programmers.jdbcproject.repository.ReviewRepository;
import com.programmers.jdbcproject.repository.UserRepository;
import com.programmers.jdbcproject.service.base.MovieService;
import com.programmers.jdbcproject.service.base.ReviewService;
import com.programmers.jdbcproject.service.base.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class DeleteService implements CrudService {

    Connection conn;
    private static final Scanner sc = new Scanner(System.in);

    public DeleteService(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void function(int type) {
        MovieService movieService = new MovieService(new MovieRepository(conn));
        ReviewService reviewService = new ReviewService(new ReviewRepository(conn));
        UserService userService = new UserService(new UserRepository(conn));
        switch (type) {
            case 1:
                movieService.delete(inputMovie());
                break;
            case 2:
                reviewService.delete(inputReviewId());
                break;
            case 3:
                userService.delete(inputUserId());
                break;
            default:
                break;
        }
    }

    private String inputMovie() {
        System.out.print("Please enter the title of the movie you want to delete :: ");
        String movieTitle = sc.nextLine();
        System.out.println();
        return movieTitle;
    }

    private int inputReviewId() {
        System.out.print("Please review id :: ");
        int reviewId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return reviewId;
    }

    private int inputUserId() {
        System.out.print("Please user id :: ");
        int userId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return userId;
    }
}

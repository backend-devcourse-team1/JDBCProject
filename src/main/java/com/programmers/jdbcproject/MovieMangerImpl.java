package com.programmers.jdbcproject;

import com.programmers.jdbcproject.MovieManager;
import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;

import java.sql.Connection;
import java.util.List;

public class MovieMangerImpl implements MovieManager {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root"; // 데이터베이스 사용자명
    private static final String PASSWORD = ""; // 데이터베이스 비밀번호


    Connection conn;

    public MovieMangerImpl(Connection conn) {
        this.conn = conn;
    }

    void Connection(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public List<Review> searchByReview(int movieId) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<Movie> orderByAudiences() {
        return null;
    }

    @Override
    public Movie searchMovie(int movieId) {
        return null;
    }

    @Override
    public List<Movie> searchReviewsByRating() {
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void insertMovie(Movie movie) {

    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    @Override
    public void insertReview(Review review) {

    }

    @Override
    public void updateReview(Review review) {

    }

    @Override
    public void deleteReview(Review review) {

    }
}
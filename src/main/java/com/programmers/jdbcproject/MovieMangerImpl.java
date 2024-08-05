package com.programmers.jdbcproject;

import com.programmers.jdbcproject.MovieManager;
import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieMangerImpl implements MovieManager {

    Connection conn;

    public MovieMangerImpl(Connection conn) {
        this.conn = conn;
    }

    void Connection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Movie> getMovies() {
        String SQL = "SELECT FROM movie";
        List<Movie> movies = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)){
            rs = pstmt.executeQuery();
            while(rs.next()){
                movies.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("rating"),
                        rs.getInt("audiences"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getString("cast"),
                        rs.getString("synopsis"),
                        rs.getString("crew"),
                        rs.getString("trailer")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return movies;
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
        String sql = "select from movie order by audiences desc";
        List<Movie> resultList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("rating"),
                        rs.getInt("audiences"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getString("cast"),
                        rs.getString("synopsis"),
                        rs.getString("crew"),
                        rs.getString("trailer")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public Movie searchMovie(int movieId) {
        return null;
    }

    @Override
    public List<Movie> orderByRating() {
        String sql = "select from movie order by rating desc";
        List<Movie> resultList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("rating"),
                        rs.getInt("audiences"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getString("cast"),
                        rs.getString("synopsis"),
                        rs.getString("crew"),
                        rs.getString("trailer")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
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
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
    public List<Review> searchByReview(int movieId){
        String sql = "SELECT * FROM review WHERE movie_id = ?";
        List<Review> reviewList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                reviewList.add(new Review(
                        rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("contents"),
                        rs.getInt("rating")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviewList;
    }


    @Override // 이거
    public List<User> getUsers() {
        String sql = "SELECT FROM user";
        List<User> users = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("nickname")
                ));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
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
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Movie movie = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                movie = new Movie(
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
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
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
        String SQL = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        String SQL = "UPDATE user set nickname = ? where id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, user.getNickname());
            pstmt.setInt(2, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        String SQL = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setInt(1, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
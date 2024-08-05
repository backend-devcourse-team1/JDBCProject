package com.programmers.jdbcproject;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MovieMangerImpl implements MovieManager {

    Connection conn;

    MovieMangerImpl(Connection conn) {
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
        String sql = "insert into movie(title, rating, audiences, genre, director, cast, synopsis, crew, trailer) " +
                "values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getRating());
            pstmt.setInt(3, movie.getAudiences());
            pstmt.setString(4, movie.getGenre());
            pstmt.setString(5, movie.getDirector());
            pstmt.setString(6, movie.getCast());
            pstmt.setString(7, movie.getSynopsis());
            pstmt.setString(8, movie.getCrew());
            pstmt.setString(9, movie.getTrailer());
            pstmt.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateMovie(int movieId, Movie movie) {
        String sql = "UPDATE movie SET " +
                "title = ?, " +
                "rating = ?, " +
                "audiences = ?, " +
                "genre = ?, " +
                "director = ?, " +
                "cast = ?, " +
                "synopsis = ?, " +
                "crew = ?, " +
                "trailer = ? " +
                "WHERE movie_id = ?";
        PreparedStatement reviewUpdate = null;
        try {
            reviewUpdate = conn.prepareStatement(sql);
            reviewUpdate.setString(1, movie.getTitle());
            reviewUpdate.setInt(2, movie.getRating());
            reviewUpdate.setInt(3, movie.getAudiences());
            reviewUpdate.setString(4, movie.getGenre());
            reviewUpdate.setString(5, movie.getDirector());
            reviewUpdate.setString(6, movie.getCast());
            reviewUpdate.setString(7, movie.getSynopsis());
            reviewUpdate.setString(8, movie.getCrew());
            reviewUpdate.setString(9, movie.getTrailer());
            reviewUpdate.setInt(10, movieId);

            reviewUpdate.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException();
        } finally {
            try {
                if (reviewUpdate != null) reviewUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteMovie(String movieTitle) {
        String sql = "delete from movie where title = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movieTitle);
            pstmt.executeUpdate();
        } catch (SQLException s) {
            throw new RuntimeException();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertReview(Review review, String nickname, String movieTitle) {
        String sql1 = "select * from user where nickname = ?";
        String sql2 = "select * from movie where title = ? ";

        String sql = "insert into review(movie_id,user_id,contents, rating) values (?,?,?,?)";

        PreparedStatement reviewPstmt = null;
        PreparedStatement userPstmt = null;
        PreparedStatement moviePstmt = null;
        ResultSet userResultSet = null;
        ResultSet movieResultSet = null;
        try {
            reviewPstmt = conn.prepareStatement(sql);
            userPstmt = conn.prepareStatement(sql1);
            moviePstmt = conn.prepareStatement(sql2);

            userPstmt.setString(1, nickname);
            userResultSet = userPstmt.executeQuery();

            moviePstmt.setString(1, movieTitle);
            movieResultSet = moviePstmt.executeQuery();

            if (userResultSet.next()) {
                if (movieResultSet.next()) {
                    reviewPstmt.setInt(1, movieResultSet.getInt("movie_id"));
                    reviewPstmt.setInt(2, userResultSet.getInt("user_id"));
                    reviewPstmt.setString(3, review.getContent());
                    reviewPstmt.setInt(4, review.getRating());
                    reviewPstmt.executeUpdate();
                } else {
                    throw new RuntimeException("영화가 존재하지 않습니다. :: " + movieTitle);
                }
            } else {
                throw new RuntimeException("존재하지 않는 사용자 입니다. :: " + nickname);
            }
        } catch (SQLException s) {
            s.printStackTrace();
            throw new RuntimeException();
        }finally {
            try {
                if (userResultSet != null) userResultSet.close();
                if (movieResultSet != null) movieResultSet.close();
                if (userPstmt != null) userPstmt.close();
                if (moviePstmt != null) moviePstmt.close();
                if (reviewPstmt != null) reviewPstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateReview(String reviewContent, int reviewId) {
        String sql = "update review set contents = ? WHERE review_id = ?";
        PreparedStatement reviewUpdate = null;
        try {
            reviewUpdate = conn.prepareStatement(sql);

            reviewUpdate.setString(1, reviewContent);
            reviewUpdate.setInt(2, reviewId);

            reviewUpdate.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (reviewUpdate != null) reviewUpdate.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "delete from review where review_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
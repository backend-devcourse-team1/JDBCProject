package com.programmers.jdbcproject;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieMangerImpl implements MovieManager {

    private Connection conn;

    MovieMangerImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Movie> getMovies() {
        String SQL = "SELECT * FROM movie";
        List<Movie> movies = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    @Override
    public List<Review> searchByReview(int movieId) {
        String sql = "SELECT * FROM review WHERE movie_id = ?";
        List<Review> reviewList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reviewList.add(new Review(
                            rs.getInt("review_id"),
                            rs.getInt("movie_id"),
                            rs.getInt("user_id"),
                            rs.getString("contents"),
                            rs.getInt("rating")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewList;
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("user_id"),
                            rs.getString("nickname")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<Movie> orderByAudiences() {
        String sql = "SELECT * FROM movie ORDER BY audiences DESC";
        List<Movie> resultList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Movie searchMovie(int movieId) {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        Movie movie = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search movie with ID: " + movieId, e);
        }
        return movie;
    }

    @Override
    public List<Movie> orderByRating() {
        String sql = "SELECT * FROM movie ORDER BY rating DESC";
        List<Movie> resultList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void insertUser(String nickname) {
        String sql = "INSERT INTO user(nickname) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nickname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert user: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET nickname = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNickname());
            pstmt.setInt(2, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertMovie(Movie movie) {
        String sql = "INSERT INTO movie(title, rating, audiences, genre, director, cast, synopsis, crew, trailer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert movie: " + e.getMessage(), e);
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
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getRating());
            pstmt.setInt(3, movie.getAudiences());
            pstmt.setString(4, movie.getGenre());
            pstmt.setString(5, movie.getDirector());
            pstmt.setString(6, movie.getCast());
            pstmt.setString(7, movie.getSynopsis());
            pstmt.setString(8, movie.getCrew());
            pstmt.setString(9, movie.getTrailer());
            pstmt.setInt(10, movieId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update movie: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteMovie(String movieTitle) {
        String sql = "DELETE FROM movie WHERE title = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieTitle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete movie: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertReview(Review review, String nickname, String movieTitle) {
        String sql1 = "SELECT * FROM user WHERE nickname = ?";
        String sql2 = "SELECT * FROM movie WHERE title = ?";
        String sql = "INSERT INTO review(movie_id, user_id, contents, rating) VALUES (?, ?, ?, ?)";

        try (PreparedStatement reviewPstmt = conn.prepareStatement(sql);
             PreparedStatement userPstmt = conn.prepareStatement(sql1);
             PreparedStatement moviePstmt = conn.prepareStatement(sql2)) {

            userPstmt.setString(1, nickname);
            try (ResultSet userResultSet = userPstmt.executeQuery()) {

                moviePstmt.setString(1, movieTitle);
                try (ResultSet movieResultSet = moviePstmt.executeQuery()) {

                    if (userResultSet.next()) {
                        if (movieResultSet.next()) {
                            reviewPstmt.setInt(1, movieResultSet.getInt("movie_id"));
                            reviewPstmt.setInt(2, userResultSet.getInt("user_id"));
                            reviewPstmt.setString(3, review.getContent());
                            reviewPstmt.setInt(4, review.getRating());
                            reviewPstmt.executeUpdate();
                        } else {
                            throw new RuntimeException("Movie does not exist: " + movieTitle);
                        }
                    } else {
                        throw new RuntimeException("User does not exist: " + nickname);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert review: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateReview(String reviewContent, int reviewId) {
        String sql = "UPDATE review SET contents = ? WHERE review_id = ?";
        try (PreparedStatement reviewUpdate = conn.prepareStatement(sql)) {
            reviewUpdate.setString(1, reviewContent);
            reviewUpdate.setInt(2, reviewId);
            reviewUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update review: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM review WHERE review_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete review: " + e.getMessage(), e);
        }
    }
}

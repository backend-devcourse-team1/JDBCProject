package com.programmers.jdbcproject.repository;

import com.programmers.jdbcproject.domain.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    Connection conn;

    public ReviewRepository(Connection conn) {
        this.conn = conn;
    }

    public void save(String movieTitle, String nickname, Review review) {

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

    public List<Review> findById(int movieId) {

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


    public void delete(int id) {
        String sql = "DELETE FROM review WHERE review_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete review: " + e.getMessage(), e);
        }
    }
    public void update(int id, String review) {
        String sql = "UPDATE review SET contents = ? WHERE review_id = ?";
        try (PreparedStatement reviewUpdate = conn.prepareStatement(sql)) {
            reviewUpdate.setString(1, review);
            reviewUpdate.setInt(2, id);
            reviewUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update review: " + e.getMessage(), e);
        }
    }

}

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

    Connection conn;

    MovieMangerImpl(Connection conn) {
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
package com.programmers.jdbcproject.repository;

import com.programmers.jdbcproject.domain.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository {

    private Connection conn;

    public MovieRepository(Connection conn) {
        this.conn = conn;
    }

    public void save(Movie movie){
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

    public List<Movie> findAll() {
        String SQL = "SELECT * FROM movie";
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findOrderByAudiences() {
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

    public List<Movie> findOrderByRating() {
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

    public Optional<Movie> findById(int id) {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setInt(1, id);
                if (rs.next()) {
                    Movie movie = new Movie(
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
                    return Optional.of(movie);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search movie with ID: " + id, e);
        }
    }

    public void update(int movieId, Movie movie) {
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

    public void delete(String movieTitle) {
        String sql = "DELETE FROM movie WHERE title = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieTitle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete movie: " + e.getMessage(), e);
        }
    }
}

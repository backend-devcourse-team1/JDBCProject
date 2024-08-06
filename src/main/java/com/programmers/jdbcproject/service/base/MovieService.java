package com.programmers.jdbcproject.service.base;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.repository.MovieRepository;

import java.util.List;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> findOrderByAudiences() {
        return movieRepository.findOrderByAudiences();
    }

    public List<Movie> findOrderByRating() {
        return movieRepository.findOrderByRating();
    }

    public Movie findMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    public void update(int movieId, Movie movie) {
        movieRepository.update(movieId, movie);
    }

    public void delete(String movieTitle) {
        movieRepository.delete(movieTitle);
    }

}

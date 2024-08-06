package com.programmers.jdbcproject.service.base;

import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.repository.ReviewRepository;

import java.util.List;

public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void save(String movieTitle, String nickname, Review review) {
        reviewRepository.save(movieTitle, nickname, review);
    }

    public List<Review> findReviewById(int movieId) {
        return reviewRepository.findById(movieId);
    }

    public void delete(int id) {
        reviewRepository.delete(id);
    }

    public void update(int id, String reviewContent) {
        reviewRepository.update(id, reviewContent);
    }
}

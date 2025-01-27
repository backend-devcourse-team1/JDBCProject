package com.programmers.jdbcproject.v1;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;

import java.util.List;

public interface MovieManager {
//    - 영화 목록 전체 조회(정원)
    List<Movie> getMovies();

//- 영화 별 리뷰 조회 (철진)
    List<Review> searchByReview(int movieId);

//- 사용자 조회(도윤)
    List<User> getUsers();

//- 관객 순 조회 (종윤) orderByAudiences
    List<Movie> orderByAudiences();

//- 영화 조회(도윤)
    Movie searchMovie(int movieId);

//- 평점 순 조회 (종윤) orderByRating
    List<Movie> orderByRating();

//- 사용자 추가 /수정 /삭제 (정원)
    void insertUser(String nickname);
    void updateUser(User user);
    void deleteUser(int id);

//- 영화 추가 /수정 /삭제 ( 종원 )
    void insertMovie(Movie movie);
    void updateMovie(int movieId, Movie movie);
    void deleteMovie(String movieTitle);

//- 리뷰 추가 /수정 /삭제 ( 종원 )
    void insertReview(Review review, String nickname, String movieTitle);
    void updateReview(String reviewContent, int reviewId);
    void deleteReview(int reviewId);
}

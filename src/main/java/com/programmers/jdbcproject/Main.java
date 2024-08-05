package com.programmers.jdbcproject;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.MovieMangerImpl;
import com.programmers.jdbcproject.domain.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/workshop";
    private static final String USER = "root"; // 데이터베이스 사용자명
    private static final String PASSWORD = "20190619"; // 데이터베이스 비밀번호

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            MovieManager movieManager = new MovieMangerImpl(connection);
            boolean loopFlag = true;
            while (loopFlag) {
                System.out.println("------- 메뉴 선택 -------");
                System.out.println("\t1. 영화 전체 조회하기");
                System.out.println("\t2. 영화 별 리뷰 조회하기");
                System.out.println("\t3. 사용자 조회하기");
                System.out.println("\t4. 관객 순 조회하기");
                System.out.println("\t5. 영화 조회하기");
                System.out.println("\t6. 평점 순 조회하기");
                System.out.println("\t7. 사용자 추가/수정/삭제하기");
                System.out.println("\t8. 영화 추가/수정/삭제하기");
                System.out.println("\t9. 리뷰 추가/수정/삭제하기");
                System.out.println("\t10. 프로그램 종료하기");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int type = Integer.parseInt(br.readLine());
                List<Movie> movie;
                switch (type){
                    case 1:
                        break;
                    case 2:
                        // 사용자로부터 movie_id 입력 받기
                        System.out.print("movie_id를 입력해주세요: ");
                        int movieId = Integer.parseInt(br.readLine());

                        // 입력받은 movie_id에 대한 리뷰 조회
                        List<Review> reviews = movieManager.searchByReview(movieId);

                        // 리뷰 정보 출력
                        for (Review review : reviews) {
                            System.out.println(review);
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        System.out.println("Add Movie : 1 | Delete Movie : 2 | Update Movie : 3");
                        System.out.print("Select option :");
                        br = new BufferedReader(new InputStreamReader(System.in));
                        int selectMovieOption = Integer.parseInt(br.readLine());
                        if (selectMovieOption == 1) {
                            movieManager.insertMovie(inputAddMovie());
                        } else if (selectMovieOption == 2) {
                            movieManager.deleteMovie(inputDeleteMovie());
                        } else if (selectMovieOption == 3) {
                            movieManager.updateMovie(intputMovieId() ,inputAddMovie());
                        } else {
                            System.out.println("Number Error");
                        }
                        break;
                    case 9:
                        System.out.println("Add Review : 1 | Delete Review : 2 | Update Review : 3");
                        System.out.print("Select option :");
                        br = new BufferedReader(new InputStreamReader(System.in));
                        int selectReviewOption = Integer.parseInt(br.readLine());
                        if (selectReviewOption == 1) {
                            String nickname = inputNickname();
                            String movieTitle = inputMovieTitle();
                            Review review = inputReview();
                            movieManager.insertReview(review,nickname,movieTitle);
                        } else if (selectReviewOption == 2) {
                            movieManager.deleteReview(deleteReview());
                        } else if (selectReviewOption == 3) {
                            int  reviewId= inputReviewId();
                            String reviewContent = inputReviewContent();
                            movieManager.updateReview(reviewContent,reviewId);
                        } else {
                            System.out.println("Number Error");
                        }
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int intputMovieId() {
        System.out.print("Please movie id :: ");
        int movieId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return movieId;
    }

    private static int inputReviewId() {
        System.out.print("Please review id :: ");
        int reviewId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return reviewId;
    }

    private static String inputReviewContent() {
        System.out.print("Please update Content :: ");
        String reviewContent = sc.nextLine();
        System.out.println();
        return reviewContent;
    }

    private static int deleteReview() {
        System.out.print("Please review id :: ");
        int reviewId = Integer.parseInt(sc.nextLine());
        System.out.println();
        return reviewId;
    }

    private static Review inputReview() {
        System.out.println("Please write a review");
        System.out.print("Content :: ");
        String reviewId = sc.nextLine();
        System.out.println();

        System.out.println("Rating :: ");
        int reviewRating = Integer.parseInt(sc.nextLine());
        System.out.println();

        return new Review(reviewId, reviewRating);
    }

    private static String inputNickname() {
        System.out.print("Please enter your nickname :: ");
        String nickname = sc.nextLine();
        System.out.println();
        return nickname;
    }
    private static String inputMovieTitle() {
        System.out.print("Please enter Movie Title :: ");
        String movieTitle = sc.nextLine();
        System.out.println();
        return movieTitle;
    }

    private static String inputDeleteMovie() {
        System.out.print("Please enter the title of the movie you want to delete :: ");
        String movieTitle = sc.nextLine();
        System.out.println();
        return movieTitle;
    }

    private static Movie inputAddMovie() {
        System.out.println("Please enter the movie information in order");
        System.out.print("title :: ");
        String title = sc.nextLine();
        System.out.println();

        System.out.print("rating :: ");
        int rating = Integer.parseInt(sc.nextLine());
        System.out.println();

        System.out.print("audiences :: ");
        int audiences = Integer.parseInt(sc.nextLine());
        System.out.println();

        System.out.print("genre :: ");
        String genre = sc.nextLine();
        System.out.println();

        System.out.print("director :: ");
        String director = sc.nextLine();
        System.out.println();

        System.out.print("cast :: ");
        String cast = sc.nextLine();
        System.out.println();

        System.out.print("synopsis :: ");
        String synopsis = sc.nextLine();
        System.out.println();

        System.out.print("crew :: ");
        String crew = sc.nextLine();
        System.out.println();

        System.out.print("trailer :: ");
        String trailer = sc.nextLine();
        System.out.println();

        return new Movie(title,rating,audiences,genre,director,cast,synopsis,crew,trailer);
    }
}
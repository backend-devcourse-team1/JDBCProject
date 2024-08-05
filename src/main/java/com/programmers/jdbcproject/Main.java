package com.programmers.jdbcproject;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.MovieMangerImpl;
import com.programmers.jdbcproject.domain.Review;
import com.programmers.jdbcproject.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/moviereview";
    private static final String USER = "root"; // 데이터베이스 사용자명
    private static final String PASSWORD = "0000"; // 데이터베이스 비밀번호


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
                        List<Movie> movies = movieManager.getMovies();
                        for (Movie getMovie : movies) {
                            System.out.println(getMovie);
                        }
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
                        System.out.println("1. 사용자 추가 2. 닉네임 변경 3. 사용자 삭제");
                        int userMode = Integer.parseInt(br.readLine());
                        switch (userMode) {
                            case 1:
                                System.out.println("닉네임을 입력해주세요");
                                String nickname = br.readLine();
                                movieManager.insertUser(new User(nickname));
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            default:
                                break;
                        }
                        break;
                    case 8:
                        break;
                    case 9:
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
}
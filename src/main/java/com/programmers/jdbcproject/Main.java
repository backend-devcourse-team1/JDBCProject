package com.programmers.jdbcproject;

import com.programmers.jdbcproject.domain.Movie;
import com.programmers.jdbcproject.MovieMangerImpl;

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
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = ""; // 데이터베이스 사용자명
    private static final String PASSWORD = ""; // 데이터베이스 비밀번호


    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("URL");
        String user = dotenv.get("USER");
        String password = dotenv.get("PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
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
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
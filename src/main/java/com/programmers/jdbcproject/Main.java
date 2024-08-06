package com.programmers.jdbcproject;


import com.programmers.jdbcproject.service.*;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Dotenv dotEnv = Dotenv.load();
    private static final String URL = dotEnv.get("DB_URL");
    private static final String USER = dotEnv.get("DB_USER"); // 데이터베이스 사용자명
    private static final String PASSWORD = dotEnv.get("DB_PASSWORD"); // 데이터베이스 비밀번호

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            CrudService crudFunction;
            boolean loopFlag = true;
            while (loopFlag) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("======================== 메뉴 선택 ========================");
                System.out.println("\t1. 조회하기\t2.추가하기\t3.수정하기\t4.삭제하기");
                System.out.println("\t5. 프로그램 종료하기\t0. 새로고침");
                System.out.println("==========================================================");
                System.out.print("\t번호를 입력해주세요: ");
                int type = Integer.parseInt(br.readLine());
                int subType;
                switch (type){
                    case 1:
                        System.out.println("======================== 메뉴 선택 ========================");
                        System.out.println("\t1. 영화 조회");
                        System.out.println("\t2. 관객 순 조회");
                        System.out.println("\t3. 평점 순 조회");
                        System.out.println("\t4. 영화 별 리뷰 조회");
                        System.out.println("\t5. 사용자 조회하기");
                        System.out.println("\t0. 처음으로");
                        System.out.println("==========================================================");
                        System.out.print("\t번호를 입력해주세요: ");
                        subType = Integer.parseInt(br.readLine());
                        System.out.println("==========================================================");
                        crudFunction = new SelectService(conn);
                        crudFunction.function(subType);
                        break;
                    case 2:
                        System.out.println("======================== 메뉴 선택 ========================");
                        System.out.println("\t1.영화 추가");
                        System.out.println("\t2.리뷰 추가");
                        System.out.println("\t3.사용자 추가");
                        System.out.println("\t0. 처음으로");
                        System.out.println("==========================================================");
                        System.out.print("\t번호를 입력해주세요: ");
                        subType = Integer.parseInt(br.readLine());
                        System.out.println("==========================================================");
                        crudFunction = new InsertService(conn);
                        crudFunction.function(subType);

                        break;
                    case 3:
                        System.out.println("======================== 메뉴 선택 ========================");
                        System.out.println("\t1.영화 수정");
                        System.out.println("\t2.리뷰 수정");
                        System.out.println("\t3.사용자 수정");
                        System.out.println("\t0. 처음으로");
                        System.out.println("==========================================================");
                        System.out.print("\t번호를 입력해주세요: ");
                        subType = Integer.parseInt(br.readLine());
                        System.out.println("==========================================================");
                        crudFunction = new UpdateService(conn);
                        crudFunction.function(subType);
                        break;
                        //관객 순 조회하기
                    case 4:
                        System.out.println("======================== 메뉴 선택 ========================");
                        System.out.println("\t1.영화 삭제");
                        System.out.println("\t2.리뷰 삭제");
                        System.out.println("\t3.사용자 삭제");
                        System.out.println("\t0. 처음으로");
                        System.out.println("==========================================================");
                        System.out.print("\t번호를 입력해주세요: ");
                        subType = Integer.parseInt(br.readLine());
                        System.out.println("==========================================================");
                        crudFunction = new DeleteService(conn);
                        crudFunction.function(subType);
                        break;
                    case 5:
                        loopFlag = false;
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
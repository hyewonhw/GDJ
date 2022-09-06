package ex01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		// OracleDriver 열기(메모리에 로드하기) + jar파일 등록(Build Path - class path) 
		// 1. oracle.jdbc.OracleDriver
		// 2. oracle.jdbc.driver.OracleDriver  -> 둘중 하나
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException e){ // 클래스 못찾으면 어떡해 예외
			System.out.println("OracleDriver 로드 실패");
		}
		
		// DriverManager로부터 Connection(DB접속) 받아오기
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  // DB마다 url은 달라짐(Oracle XE 버전 기준) 1521->포트번호
			String user = "SCOTT";          					 // 대소문자 상관없음
			String password = "TIGER";      					 // 대소문자 지켜야함
			// git.hub에 id와 pw 올라가면 안됨
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB접속성공");
		} catch (SQLException e) { 	// 접속이 안된거임
			System.out.println("DB접속정보 오류"); 
		}
		
		// ★★★Connection 종료★★★
		// JDBC에서는 Connection을 닫는 것이 굉장히 중요
		// 메소드 실행할 떄 마다 접속 - 접속종료 해줘야 돌아감
		try {
			if(con != null) 
				con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}

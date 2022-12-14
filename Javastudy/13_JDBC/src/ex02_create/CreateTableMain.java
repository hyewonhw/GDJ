package ex02_create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableMain {

	public static void main(String[] args) {
		
		// Connection
		// 항상 가장 먼저 해야 할 일 
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";          					 
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException e) {
			System.out.println("OracleDriver 로드 실패");
		} catch(SQLException e) {
			System.out.println("DB접속정보 오류");
		}
		
		// CREATE TABLE 실행
		PreparedStatement ps = null;
		try {
			
			// String 타입의 쿼리문 작성
			// 쿼리문의 마지막 세미콜론(;)은 JDBC에서 사용하지 않는다!
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE BOARD(");
			sb.append("BOARD_NO NUMBER NOT NULL CONSTRAINT PK_BOARD PRIMARY KEY,");   // ctrl + shift + x -> 대문자로 변경 (y는 소문자)
			sb.append("TITLE VARCHAR2(100 BYTE) NOT NULL,");  // 제목
			sb.append("CONTENT VARCHAR2(100 BYTE) NULL,");    // 내용
			sb.append("HIT NUMBER NOT NULL,");  			  // 조회수
			sb.append("CREATE_DATE DATE NOT NULL)");		  // 작성일자
			String sql = sb.toString();
			
			// PreparedStatement 객체 생성
			// Prepared : 미리 준비하라.
			// Statement : 쿼리문		
			// 역할 : 쿼리문 실행을 담당
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// Connection 닫기, PreparedStatement닫기
		try {
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
	}

}

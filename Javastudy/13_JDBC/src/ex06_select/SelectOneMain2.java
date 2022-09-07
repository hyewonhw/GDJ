package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneMain2 {

	public static void main(String[] args) {
		
		// 집계 함수 결과 조회
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 
			Class.forName("oracle.jdbc.OracleDriver");
			
			// Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";          					 
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(*) AS 총개수 FROM BOARD"; // 게시판의 개수 
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery(); // 쿼리문 실행
			
			if(rs.next()) {
				
				/*
				 	|총개수|
				    |  3   | <= rs.next() 호출로 인해 현재 rs 포인터의 위치
				    
				    3을 가지고오는 방법 2가지
				    1. rs.getInt("총개수")
				    2. rs.getInt(1)
				*/
				
				int count = rs.getInt("총개수");
				System.out.println(count);
				
			} // 집계 함수의 결과는 else가 나올 수 없음 (else 처리할 필요가 없음) -> 결과가 안나올 일이 없음 (게시글이 없으면 0이라고 나오기때문)
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}

package ex03_insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class InsertMain {

	public static void main(String[] args) {
		
		// 게시판 정보 입력 받아서 BOARD 테이블에 저장하기
		// 1. Scanner 클래스로 정보 입력 받기
		// 2. Board 클래스 타입의 객체에 입력 받은 정보 저장하기
		
		Scanner sc = new Scanner(System.in);
		
		// 제목 입력받기
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		
		// 내용 입력받기
		System.out.print("내용 >>> ");
		String content = sc.nextLine();
		
		Board board = new Board(); // 패키지 다르기 때문에 import필요
		board.setTitle(title);
		board.setContent(content);
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";          					 
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			// ? : 변수/ + board.getTitle()을 사용하면 보안에 취약/ 조회수는 처음에 0
			String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, 0, SYSDATE)";
			
			// PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 쿼리문에 포함된 ?에 변수 전달하기
			// 쿼리문에 작성된 ?의 순서대로 명시하며(적어주며) 채워줌
			ps.setString(1, board.getTitle()); 		// 1번째 ?에 board.getTitle() 전달하기(title이 String 이므로 setString)			
			ps.setString(2, board.getContent());    // 2번째 ?에 board.getContent() 전달하기()(title이 String 이므로 setString)
			
			//INSERT문의 실행
			// 1. executeUpdate() 메소드 사용하기
			// 2. executeUpdate() 메소드의 반환값은 int 타입
			// 3. 반환값
			// 	  1) 1이 반환되는 경우 : 1개의 행이 INSERT되었다는 의미. 성공을 의미함
			//    2) 0이 반환되는 경우 : 0개의 행이 INSERT되었다는 의미. 실패를 의미함
			int result = ps.executeUpdate();  // 실행이 executeUpdate
			if(result > 0) { 
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 

	}

}

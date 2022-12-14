package com.gdu.app07.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;


/*
	@Repository
	DAO에 추가하는 @Component
	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색됨.
	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어 짐.
*/


@Repository // DAO가 사용하는 @Component로 트랜잭션 기능이 추가되어 있음.


public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	/***************************************************************/
	// META-INF폴더생성 후 context.xml추가함 (06_jdbc랑 비교하면서 보기)
	
	// Connection 직접 작성하지 않음
	// Connection Pool을 관리하는 DataSource를 필드로 추가함
	private DataSource dataSource;
	
	// BoardDAO가 생성되면 
	// context.xml의 Resource를 읽어서 dataSource객체를 만든다
	public BoardDAO(){
		// JNDI : Resource의 name을 이용해서 읽어들이는 방식
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
		} catch (Exception e) { // NamingException
			e.printStackTrace();
		}
	}
	/**********************************************************************/
	
	private void close() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(con != null) {con.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 레파지토리 계층의 이름은 "DB 친화적으로" 작성
	
	public List<BoardDTO> selectAllBoards() {
		List<BoardDTO> boards = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();  // dataSource가 가지고있는 getConnection임, Connection Pool에서 Connection을 하나 얻어 온다는 뜻
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { // 커서를 옮긴다 : 한줄(행)씩 커서를 옮겨감 (10행이면 10번커서옮겨감), 한번호출당 한행씩
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				boards.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boards;
	}
	
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}
	
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE)"
				+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))"; // board 안에 ?에들어갈 값이 들어있어야함
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}	
	
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
}

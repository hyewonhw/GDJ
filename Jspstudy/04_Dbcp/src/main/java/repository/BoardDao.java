package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

//***** 8 *****//
// DAO -> Service -> Controller 순으로 작업하는게 빠름
public class BoardDao {
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection Pool 관리
	private DataSource dataSource;
	
	// singleton - pattern
	private static BoardDao dao = new BoardDao(); // 하나 미리 만들어둠, private라 내부에서만 만들수있음
	private BoardDao() {
		try {
			// DataSource 객체 생성
			// context.xml에서 name="jdbc/oracle11g"인 Resource를 찾아서 생성(JNDI방식)
			Context ctx = new InitialContext();
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");
			// dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11g");    	-> 위 두줄 합침
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	// static 필드(dao) 사용하기때문에 메소드도 staic 사용
	public static BoardDao getInstance() {
		return dao;
	}
	
	// method
	
	// 1. 접속/자원 해제
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (ps != null) { ps.close(); }
			if (con != null) { con.close(); }  // Connection Pool의 close()는 Connection종료가 아닌 Connection반환으로 동작
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. 목록 반환하기
	public List<Board> selectAllBoards() {
		List<Board> boards = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();  // Connection Pool로부터 Connection 대여
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);  // Statement = 쿼리문
			rs = ps.executeQuery();   // SELECT문은 executeQuery() 사용-SELECT문 돌릴때 사용
			// 최초 rs는 아무것도 가리키고있지않음 -> rs.next() -> rs.next()
			// board_no 가져올때 rs.getInt("board_no") 또는 칼럼번호를 써서 rs.getInt(1) rs.getString(), rs.getDate()
			while (rs.next()) {  // 목록보기는 while문 처리
				// Board board는 한 개의 게시글을 의미함
				Board board = new Board();
				board.setBoard_no(rs.getInt(1));     // rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2)); 	 // rs.getString("TITLE")
				board.setContent(rs.getString(3));   // rs.getString("CONTENT")
				board.setCreate_date(rs.getDate(4)); // rs.getDate("CREATE_DATE")
				// 가져온 게시글을 리스트에 추가함
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}	
	
	// 3. 상세보기
	public Board selectBoardByNo(int board_no) {
		Board board = null; // ex)없는번호의 게시글을 요청할때 null을 반환할줄알아야하기때문에 null로 
		try {
			con = dataSource.getConnection();   // connection 선 구걸 필수
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);     // 1번째 물음표(?)에 board_no 전달하기(no가int값이니까 메소드도 setInt)
			rs = ps.executeQuery();     // SELECT문은 executeQuery() 사용
			if(rs.next()) {             // 상세보기는 if문
				board = new Board();    // 조회된 결과가 있으면 만들어줌
				board.setBoard_no(rs.getInt(1));     // rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2)); 	 // rs.getString("TITLE")
				board.setContent(rs.getString(3));   // rs.getString("CONTENT")
				board.setCreate_date(rs.getDate(4)); // rs.getDate("CREATE_DATE")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);   // connection 반납
		}
		
		return board;
	}
	
	// 4. 게시글 삽입
	public int insertBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());     // 첫번째물음표에 title 넣기
			ps.setString(2, board.getContent());   // 두번째물음표에 content 넣기
			result = ps.executeUpdate();    	   // INSERT문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);   	// rs는 select문에서만 쓰기때문에 null
		}
		return result;
	}
	
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();    	   // UPDATE문은 executeUpdate() 메소드 사용
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	
	
	
	
	
}

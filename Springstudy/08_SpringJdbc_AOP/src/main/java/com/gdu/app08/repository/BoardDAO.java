package com.gdu.app08.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app08.domain.BoardDTO;

@Repository

public class BoardDAO {

	// JdbcTemplate
	// Connection, PreparedStatement, ResultSet을 내부에서 사용하는 스프링 클래스
	// DriverManagerDataSource에 의해서 Connection Pool 방식으로 동작
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BoardDTO> selectAllBoards() {
	    String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
	    
		/***************************************************************************************************************************/
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));  // 이름같으면 알아서 매핑해주겠다 / query 반환타입이 List<BoardDTO>임
		//List<BoardDTO> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
		//return boards;
		/******************************************************************************************************************************************************/
	
	}
	
	public BoardDTO selectBoardByNo(final int board_no) {  // 예전에 매개변수 해킹 시도있었어서 final 붙임(안쓰면 실행막히게함)
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), board_no);  // queryForObject : 쿼리 결과가 하나
		return board;
	}
	
	public int insertBoard(final BoardDTO board) {
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE) "
				   + "VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
				
			}
		});
		return result;
	}
	
	public int updateBoard(final BoardDTO board) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {	
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());
			}
		});
		return result;
	}
	
	public int deleteBoard(final int board_no) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {	
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, board_no);
			}
		});
		return result;
	}

}

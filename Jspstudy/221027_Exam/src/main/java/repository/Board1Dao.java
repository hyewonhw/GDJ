package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board1;

public class Board1Dao {
	private SqlSessionFactory factory;
	
	private static Board1Dao dao = new Board1Dao();
	private Board1Dao() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Board1Dao getInstance() {
		return dao;
	}
	
	String mapper = "mybatis.mapper.board1.";
	
	/* 목록 */
	public List<Board1> selectAllBoards(){
		SqlSession ss = factory.openSession();
		List<Board1> boards = ss.selectList(mapper + "selectAllBoards");
		ss.close();
		return boards;
	}
	
	/* 새글작성 */
	public int insertBoard(Board1 board1) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertBoard", board1);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}	
	
	/* 상세 */
	public Board1 selectBoardByNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board1 board1 = ss.selectOne(mapper + "selectBoardByNo", boardNo);
		ss.close();
		return board1;
	}
	
	/*  */ 
	


}

package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Free;

public class FreeDAO {
	
	private SqlSessionFactory factory;
	
	private static FreeDAO dao = new FreeDAO();
	private FreeDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static FreeDAO getInstance() {
		return dao;
	}
	
	String mapper = "mybatis.mapper.free.";
	
	// list
	public List<Free> selectAllFreeBoards() {
		SqlSession ss = factory.openSession();
		List<Free> freeList = ss.selectList(mapper + "selectAllFreeBoards");
		ss.close();
		return freeList;
	}
	
	// detail
	public Free selectFreeBoardByNo(long freeNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne(mapper + "selectFreeBoardByNo", freeNo);
		ss.close();
		return free;
	}
	
	// add
	public int insertFreeBoard(Free free) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertFreeBoard", free);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// remove
	public int deleteFreeBoard(long freeNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteFreeBoard", freeNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// modify
	public int updateFreeBoard(Free free) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update(mapper + "updateFreeBoard", free);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// hit
	public int updateHit(long freeNo) {
		int res = 0;
		return res;
	}
}

package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDao {

	// field - SqlSessionFactory
	private SqlSessionFactory factory;
	
	// singleton pattern
	private static MemberDao dao = new MemberDao();
	private MemberDao() {
		try {
			// SqlSessionFactory 빌드
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDao getInstance() {
		return dao;
	}
	
	// method (id와 뭐시기 통일)
	
	String mapper = "mybatis.mapper.member.";
	
	// 1. 회원목록
	public List<Member> selectAllMembers() {
		SqlSession ss = factory.openSession();
		List<Member> members = ss.selectList(mapper + "selectAllMembers");
		ss.close();
		return members;
	}
	
	// 2. 회원수
	public int selectAllMembersCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllMembersCount");
		ss.close();
		return count;
	}
	
	// 3. 회원상세
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(mapper + "selectMemberByNo", memberNo);
		ss.close();
		return member;
	}
	
	// 4. 회원등록
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertMember", member);
		if(result > 0) {   // result가 0보다 크면 성공 -> commit
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 5. 회원수정
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update(mapper + "updateMember", member);  // insert나 delete라고 해놔도 updateMember로가서 update함
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 6. 회원삭제
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteMember", memberNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
}
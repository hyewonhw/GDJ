package com.gdu.app10.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app10.domain.BoardDTO;

/*
	@Mapper
	mybatis의 매퍼와 직접 연결되는 인터페이스
	@Mapper가 가진 메소드를 호출하면 매퍼의 쿼리문이 바로 호출됨
	메소드명은 쿼리문의 id와 같아야 함
	@Mapper 찾으려면 @MapperScan 필요 (DBconfig에 추가)
*/
@Mapper

public interface BoardMapper {
	public List<BoardDTO> selectAllBoards();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);
}

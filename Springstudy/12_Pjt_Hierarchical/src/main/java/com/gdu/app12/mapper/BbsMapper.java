package com.gdu.app12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app12.domain.BbsDTO;

@Mapper
public interface BbsMapper {
	public int selectAllBbsCount();
	public List<BbsDTO> selectAllBbsList(Map<String, Object> map);
	public int insertBbs(BbsDTO bbs);       // 원글 삽입
	// 그룹오더로 댓글 순서 바꿈 => insertReply로 댓글넣고 기존에 있던 댓글들 update해줌
	public int updatePrevious(BbsDTO bbs);  // 답글 삽입 전 기존 답글에 GROUP_ORDER 업데이트
	public int insertReply(BbsDTO bbs);     // 답글 삽입
	public int deleteBbs(int bbsNo);
}

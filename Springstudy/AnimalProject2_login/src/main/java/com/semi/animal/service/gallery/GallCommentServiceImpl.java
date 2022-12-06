package com.semi.animal.service.gallery;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.animal.domain.gallery.GallCommentDTO;
import com.semi.animal.mapper.gallery.GallCommentMapper;
import com.semi.animal.util.PageUtil;

@Service
public class GallCommentServiceImpl implements GallCommentService {

	@Autowired
	private GallCommentMapper gallCommentMapper;
	
	@Autowired
	private PageUtil pageUtil;
	
	@Override
	public Map<String, Object> getCommentCount(int gallNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gallCommentCount", gallCommentMapper.selectGallCommentCount(gallNo));
		return null;
	}
	
	
	@Override
	public Map<String, Object> addComment(GallCommentDTO gallComment) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isGallCommentAdd", gallCommentMapper.insertGallComment(gallComment) == 1);
		return null;
	}
	
	@Override
	public Map<String, Object> gatCommentList(HttpServletRequest request) {
		int gallNo = Integer.parseInt(request.getParameter("gallNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		int gallCommentCount = gallCommentMapper.selectGallCommentCount(gallNo);
		
		pageUtil.setPageUtil(page, gallCommentCount);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gallNo", gallNo);
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gallCommentList", gallCommentMapper.selectGallCommentList(map));
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	
	@Override
	public Map<String, Object> removeComment(int gallCmtNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isGallCommentRemove", gallCommentMapper.deleteGallComment(gallCmtNo) == 1); // true
		return result;
	}
	
	@Override
	public Map<String, Object> addReply(GallCommentDTO reply) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isGallCommentAdd", gallCommentMapper.insertReply(reply) == 1); // 1과 같으면 true
		return result;
	}
	
}

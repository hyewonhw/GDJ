package com.semi.animal.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.semi.animal.domain.GallBoardDTO;

public interface GallBrdService {
	public void getGallBoardList(Model model);
	public void saveGallBrd(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest mutipartRequest);
	public int increaseGallHit(int gallNo);
	public GallBoardDTO getGallbrdByNo(int gallNo);
	public void modifyGallBrd(HttpServletRequest request, HttpServletResponse response);
	public void removeGallBrd(HttpServletRequest request, HttpServletResponse response);
}

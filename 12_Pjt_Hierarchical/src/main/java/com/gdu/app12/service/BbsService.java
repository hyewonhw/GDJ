package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {
	public void findAllBbsList(HttpServletRequest request, Model model);
	public int addBbs(HttpServletRequest request);
	public int addReply(HttpServletRequest request);
	public int removeBbs(int bbsNo);  // IP때문에 request함 / 삭제는 IP 필요없으니까 No로 삭제함
}

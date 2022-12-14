package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MemberService {
	public ActionForward login(HttpServletRequest request, HttpServletResponse response);
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response);
	public void register(HttpServletRequest request, HttpServletResponse response);
	public void cancle(HttpServletRequest request, HttpServletResponse response);
	// 아이디 중복 체크
}

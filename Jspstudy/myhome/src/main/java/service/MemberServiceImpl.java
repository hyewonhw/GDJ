package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceImpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.build();
		
		Member login = MemberDao.getInstance().login(member);

		if(login != null) {
			// 성공했을때
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			return new ActionForward("/index.jsp", false);
		} else {
			// 실패했을때
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} 
		
	}

	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return new ActionForward(request.getContextPath(), true);

	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}

package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board1;
import repository.Board1Dao;

public class Board1AddService implements Board1Service {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// DB로 보내기 위해 Board board 생성
		Board1 board1 = new Board1();
		board1.setTitle(title);
		board1.setContent(content);
		
		// DB로 Board board 보내기(삽입)
		int result = Board1Dao.getInstance().insertBoard(board1);
		
		// 삽입 성공 / 실패 응답
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('삽입성공')");
			out.println("location.href='" + request.getContextPath() + "/board1/list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('삽입실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		
		return null;
	}

}

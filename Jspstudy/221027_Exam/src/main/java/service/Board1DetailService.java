package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board1;
import repository.Board1Dao;

public class Board1DetailService implements Board1Service {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		Board1 board1 = Board1Dao.getInstance().selectBoardByNo(boardNo);
		
		if(board1 == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + boardNo + "번 게시글 정보가 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board1/list.do'");
			out.println("</script>");
			out.close();
		} else {
			request.setAttribute("board", board1);
			ActionForward af = new ActionForward();
			af.setView("/board1/detail.jsp");
			af.setRedirect(false);
			return af;
		}	
		return null;
		
	}

}

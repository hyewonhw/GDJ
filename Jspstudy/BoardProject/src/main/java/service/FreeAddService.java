package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeAddService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Free free = new Free();
		free.setWriter(writer);
		free.setTitle(title);
		free.setContent(content);
		
		int res = FreeDAO.getInstance().insertFreeBoard(free);
		
		return new ActionForward("free/insert.jsp?res=" + res, true);
	}

}

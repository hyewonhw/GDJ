package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.Board1Dao;

public class Board1ListService implements Board1Service {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("boards", Board1Dao.getInstance().selectAllBoards());
		
		ActionForward af = new ActionForward();
		af.setView("/board1/list.jsp");
		af.setRedirect(false);
		return af;
	}

}

package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import repository.FreeDAO;

public class FreeListService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// FreeDAO dao = FreeDAO.getInstance();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("updateHit") != null) {
			session.removeAttribute("updateHit");
		}
		
		request.setAttribute("freeList", FreeDAO.getInstance().selectAllFreeBoards());
		
		return new ActionForward("free/list.jsp", false);
		
	}


}

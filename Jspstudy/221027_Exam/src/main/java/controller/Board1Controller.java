package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.Board1AddService;
import service.Board1DetailService;
import service.Board1ListService;
import service.Board1ModifyService;
import service.Board1RemoveService;
import service.Board1Service;


@WebServlet("*.do")

public class Board1Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		Board1Service service = null;
		
		ActionForward af = null;
		
		switch(urlMapping) {
		case"/board1/list.do":
			service = new Board1ListService();
			break;
		case"/board1/add.do":
			service = new Board1AddService();
			break;
		case"/board1/detail.do":
			service = new Board1DetailService();
			break;
		case"/board1/write.do":
			af = new ActionForward();
			af.setView("/board1/write.do");
			af.setRedirect(false);
			break;
		case"/board1/remove.do":
			service = new Board1RemoveService();
			break;
		case"/board1/modify.do":
			service = new Board1ModifyService();
			break;
		}
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

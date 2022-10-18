package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardEditService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardService;

import java.io.IOException;

@WebServlet("*.do")

public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//***** 3 *****//
		// 요청&응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("test/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length() +1);
		
		// BoardService 선언
		BoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// 요청(urlMapping)에 따른 Service 생성
		// 3 - switch 뼈대만 만들어둠 - 각 서비스클래스 만들때마다 case 추가
		switch(urlMapping) {
		// 비즈니스 로직이 있는 경우
		case "board/list.do":
			service = new BoardListService();
			break;
		case "board/detail.do":
			service = new BoardDetailService();
			break;
		case "board/add.do":
			service = new BoardAddService();
			break;
		case"board/edit.do":
			service = new BoardEditService();
			break;
		case"board/modify.do":
			service = new BoardModifyService();
			break;
		// 비즈니스 로직이 필요없는 단순이동
		case"board/write.do":
			af = new ActionForward();
			af.setView("/board/write.jsp");
			af.setRedirect(false);  // 단순이동은 forward
			break;
		}
		
		// Service 실행
		// 7 - Exception(BoardService)이 넘어와도 IOException(BoardController)이 넘겨줄수가없어서 try-catch
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		// 어디로 어떻게?
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

package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AdderService;
import service.MyService;
import service.NowService;
import service.TodayService;

// @WebServlet({"/today.do", "now.do", "adder.do"})
@WebServlet("*.do")  // .do로 끝나는 모든 요청

public class MyController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 & 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 요청 확인(/today.do인지 /now.do인지)
		String requestURI = request.getRequestURI();					   // requestURI  : /03_Mvc/today.do 또는 /03_Mvc/now.do
		String contextPath = request.getContextPath();					   // contextPath : /03_Mvc
		String command = requestURI.substring(contextPath.length() + 1);   // command     : today.do 또는 now.do
		
		// MyService 선언
		// MyService 인터페이스 추가 후
		MyService myService = null;
		
		// ActionForward 선언
		// 1. 모든 Model의 execute() 메소드의 반환타입은 ActionForward이다!
		// 2. Model이 필요 없는 경우 ActionForward를 직접 만든다!
		ActionForward actionForward = null;
		
		// 요청에 따른 Model의 선택
		switch(command) {
		
		// 비즈니스 로직이 필요한 경우
		case "today.do":
			// TodayService todayService = new TodayService(); 를 
			myService = new TodayService();  // myService로 수정
			// todayService.execute(request, response);   // TodayService를 불러서 execute 넘김 실행은 Service가
			// myService.execute(request, response);      // myService로 수정
			break;
		case "now.do":
			myService = new NowService();
			break;
		case "adder.do":
			myService = new AdderService();
			break;
		// 비즈니스 로직이 필요 없는 단순이동의 경우(직접 만든경우)
		case "input.do":		// 이동만 하기 때문에 Model 필요없음(Myservice선언안함)
			actionForward = new ActionForward();
			actionForward.setView("views/input.jsp");
			break;
		}
		
		// 선택한 Model의 실행
		if(myService != null) {
			actionForward = myService.execute(request, response);   // 선택과 실행을 분리
		}
		
		// 이동(리다이렉트, 포워드)
		// 1. actionForward != null : 리다이렉트 또는 포워드
		// 2. actionForward == null : response로 응답한 경우 또는 ajax 처리
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getView());
			} else {
				request.getRequestDispatcher(actionForward.getView()).forward(request, response); // result.jsp로 today 보냄
				// 이동하고자 하는 view인 result.jsp를 변수처리함 -> actionForward				
			}
		}
		
		/*
			수업순서
		 	index.jsp : a링크 href타고 Today.java실행
		 	Today.java : Servlet, TodayService실행, today값을 result.jsp로 보냄
		 	TodayService.java : today 속성 값으로 저장 result에
		 	result.jsp : result 속성값 화면에 뿌림
		 	
		 	MyService인터페이스 생성 : execute 넣어놓고 각 Service에 implements MyService + @override 추가
		 	Today.java에 MyService 선언 후 선택에서 new로 생성
		 	
		 	input.jsp생성
		 	ActionForward에 변수 view 생성 후 게터세터
		 	TS,NS에 void 를 ActionForward로 변경
		 	
		 	패키지 분리
		 	Today.java 이름 MyController로 변경
		*/ 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import sevice.NaverCaptchaService;
import sevice.NaverCaptchaServiceImpl;

@WebServlet("*.do")

public class MemberController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// NaverCaptchaServiceImpl 객체 생성
		NaverCaptchaService service = new NaverCaptchaServiceImpl();
		
		// ActionForward 객체 -> 딱 한번쓰임
		ActionForward af = null;
		
		// 요청에 따른 Service 선택 및 실행
		switch(urlMapping) {
		case"/member/loginPage.do":
			// 캡차키 발급 요청
			String key = service.getCaptchaKey();
			// 캡차이미지 발급 요청
			Map<String, String> map = service.getCaptchaImage(request, key);
			request.setAttribute("dirname", map.get("dirname"));
			request.setAttribute("filename", map.get("filename"));
			request.setAttribute("key", map.get("key"));
			// ActionForward 생성
			af = new ActionForward("/member/login.jsp", false); // 포워드
			break;
		case"/member/refreshCaptcha.do":  // ajax이동
			service.refreshCaptcha(request, response);
			break; 
		case"/member/validateCaptcha.do":
			boolean result = service.validateUserInput(request);
			if(result) { // result가 true이면
				af = new ActionForward("/member/success.jsp", false);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('자동입력 방지문자를 확인하세요.');");
				out.println("location.href='" + request.getContextPath() + "/member/refreshCaptcha.do'");
				out.println("</script>");
				out.close();
			}
		}
		
		// 어디로 어떻게 이동하는가?
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

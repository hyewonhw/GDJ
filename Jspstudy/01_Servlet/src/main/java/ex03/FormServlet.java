package ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormServlet")

public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 변수(파라미터)
		String id = request.getParameter("id");
		if(id.isEmpty()) {
			// 아무것도 입력안하고 보내면 빈문자열/빈택배박스
			id = "빈아이디";
		}
		/*
		===== 가능한 코드 =====
		--> id == null 이면 || 뒤 코드는 실행 안하기때문에 NullPointException안 떨어짐
		--> 무조건 null인지 확인하는게 먼저
		if(id == null || id.isEmpty()) {
			id = "빈아이디";
		}
		===== 불가능한 코드 =====
		--> NullPointException : id가 null이여서 null.isEmpty()가 됨
		if(id.isEmpty() || id == null) {
			id = "빈아이디";
		}
		*/
		
		String pwd = request.getParameter("pwd");
		if(pwd.isEmpty()) {
			// 아무것도 입력안하고 보내면 빈문자열(전송함)/빈택배박스
			pwd = "빈 비밀번호";
		}
		
		String gender = request.getParameter("gender");  // 클라이언트측에서 value값이 넘어옴 
		if(gender == null) {
			// 아무것도 선택안하고 보내면 null값(전송안함)/택배아예안감
			gender = "빈 성별";
		}
		
		String city = request.getParameter("city");
		if(city.isEmpty()) {
			// 아무것도 입력안하고 보내면 빈문자열(전송함)/빈택배박스
			city = "빈 도시";
		}
		
		// 배열(파라미터)
		String[] phone = request.getParameterValues("phone");
		if(phone[0].isEmpty()) {
			phone[0] = "빈 전화1";
		}
		if(phone[1].isEmpty()) {
			phone[1] = "빈 전화2";
		}
		if(phone[2].isEmpty()) {
			phone[2] = "빈 전화3";
		}
		String strPhone = phone[0] + "-" + phone[1] + "-" + phone[2];
		
		String[] agree = request.getParameterValues("agree");
		if(agree == null) {
			// 아무것도 선택안하고 보내면 null값(전송안함)/택배아예안감
			
		}
		
		// -> 전송문제는 프론트/서버측 둘다 해주기
		
		
		
		// 연습(이메일)
		String emailId = request.getParameter("email_id");
		String domain = request.getParameter("domain");
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();    // pw 쓰기위해선 반드시 예외처리 필요
		out.println("<h3>아이디 : " + id + "</h3>");
		out.println("<h3>비밀번호 : " + pwd + "</h3>");
		out.println("<h3>성별 : " + gender + "</h3>");
		out.println("<h3>도시 : " + city + "</h3>");
		out.println("<h3>연락처 : " + strPhone + "</h3>");
		out.println("<h3>동의여부 : " + Arrays.toString(agree) + "</h3>");
		out.println("<h3>이메일 : " + emailId + "@" + domain + "</h3>");
		List<String> list = Arrays.asList(agree);	// 문자열 배열이 List로 변환됨
		if(list.contains("marketing")) { // 마케팅을 체크해서 보냈는지
			out.println("<h3>마케팅 동의한 회원</h3>");
		}
		out.close();	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.gdu.app02.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 	@Controller
 	
  	컨트롤러임
  	@Component에 의해서 자동으로 Bean으로 만들어지고 스프링에 의해서 사용됨
*/

@Controller
public class MvcController {

	// 메소드 1개 : 요청 1개와 응답 1개를 처리하는 단위 (switch로 분리하던걸 메소드로하면됨)
	// 항시!! 메소드 먼저 만들고 마지막에 애너테이션 붙붙
	
	/*
	 	@RequestMapping
	 	요청을 인식하는 애너테이션
	 	매핑과 요청 메소드()를 인식함
	 
	 	속성
	 		1) value  : URL Mapping
	 		2) method : RequestMethod 	
	*/
	
	// welcome 파일 작업하기
	// URLMapping으로 "/"를 요청하면 "/WEB-INF/views/index.jsp"를 열어준다.
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	
	
	// 메소드 작성 방법 (접근권한 걍 public)
	// 1. 반환타입 : String (응답할 뷰(jsp)의 이름을 반환)
	// 2. 메소드명 : 아무 일도 안함. 맘대로 작성
	// 3. 매개변수 : 선택 (요청이 있으면 request, 응답을 만들면 response 등)
	
	public String welcome() {
		return "index";  // DispatcherServlet의 ViewResolver에 의해서 해석된다. (servlet-context확인)
						 // prefix="/WEB-INF/views/"
						 // suffix=".jsp"
						 // prefix와 suffix에 의해서 "/WEB-INF/views/index.jsp"와 같이 해석되고 처리된다.
		
		// index.jsp로 forward했을까? redirect했을까?
		// 정답 : forward
		// redirect할 때는 return "redirect:이동할경로"; 처럼 반환 
		// 적지않으면 알아서 forward -> forward코드 사라짐 안써도됨!
	}
	
	
	// <a href="${contextPath}/animal">
	@RequestMapping(value="/animal", method=RequestMethod.GET)
	public String 동물보러가기() {
		
		//  /WEB-INF/views/ + gallery/animal + .jsp
		
		return "gallery/animal";
	}
	
	//@RequestMapping(value="/animal", method=RequestMethod.GET)
	//@RequestMapping(value="animal", method=RequestMethod.GET)   슬래시 생략가능	
	//@RequestMapping(value="/animal")							  GET생략 가능
	//@RequestMapping("/animal")								  value로 인식함
	//@RequestMapping("animal")								      최종버전
	
	
	// <a href="${contextPath}/flower">
	@RequestMapping("flower")
	public String 꽃보러가기() {
		
		// return "/gallery/flower"  슬래시(/)가 있어도 됨
		
		return "gallery/flower";  // 슬래시(/)가 없어도 됨
		
		
	}
	
	
	// <a href="${contextPath}/animal/flower">
	@RequestMapping("animal/flower")
	public String 동물보고꽃보고() {
		
		// redirect: 다음에는 항상 다른 URL Mapping을 적어 준다.
		
		return "redirect:/flower";    
	}
	
	
	// <a href="${contextPath}/want/animal?filename=animal5.jsp">animal5 보러가기</a>
	@RequestMapping("want/animal")
	public String 동물5보기(HttpServletRequest request) {
		
		System.out.println(request.getParameter("filename"));
		
		return "gallery/animal5";
		
	}
	
	
	// <a href="${contextPath}/response">
	@RequestMapping("response")
	public void 응답만들기(HttpServletRequest request,  HttpServletResponse response) {
		
		// 응답 만들때는 return 하지않음
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('동물 보러 가자');");
			out.println("location.href='" + request.getContextPath() + "/animal';");
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
	
	
	
	
	
	
	
}

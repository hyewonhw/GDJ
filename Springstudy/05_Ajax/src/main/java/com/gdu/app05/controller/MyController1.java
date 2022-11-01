package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;


@Controller
public class MyController1 {
	
	@GetMapping("/")
	public String welcome() {
		return "index";    // index.jsp로 forward
	}
	
	@GetMapping("member")
	public String member() {
		return "member";   // member.jsp로 forward
	}
	
	
	// field
	@Autowired  // Container(root-context.xml)에서 타입(class)이 일치하는 bean을 가져오세요.
	private MemberService memberService;  // 일반적으로 같은이름(id="service") 쓰지만 다른이름써도 괜츈
	
	/*
		@ResponseBody
		ajax 처리하는 메소드
		반환하는 값은 뷰(JSP)이름이 아닌, 어떤 데이터(text, json, xml 등)임
		
		ajax는 요청과 응답이 한 페이지에서 이루어짐, jsp로의 이동을 막아야함(return str만 작성하면 str.jsp로이동)
	*/
	
	// 1. 반환타입 String + request로 받기
	@ResponseBody
	@GetMapping(value="member/detail1"
			  , produces="text/plain; charset=UTF-8") // produces : 응답 데이터의 타입(MIME-TYPE, 이 메소드가 무엇을 만드느냐 적으면됨)
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		String str = memberService.execute1(request, response);
		return str; 
	}
	
	
	
	// 2. Member 반환 + 변수로 받기
	@ResponseBody
	@GetMapping(value="member/detail2"
	          , produces="application/json; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
		Member member = memberService.execute2(id, pw);
		return member;    // jackson이 member객체를 {"id":아이디, "pw":패스워드} 형식의 JSON으로 바꿔서 전달
		/*
			jackson없으면 이 코드 써야함
			JSONObject obj = new JSONObject(member);
			return obj.toString();
		*/
	}
	
	
	
	@ResponseBody
	@GetMapping(value="member/detail3"
			  , produces=MediaType.APPLICATION_JSON_VALUE) // produces="application/json"
	public Map<String, Object> detail3(Member member) {
		Map<String, Object> map = memberService.execute3(member);
		return map;
	} // return memberService.excute3
	
	
	
	/*
		@RequestBody
		요청 데이터가 body에 포함되어있다고 알려줌
		요청 파라미터에서는 사용 할 수 없음
		post방식으로 파라미터 없이 데이터 전달될 때 사용
	*/
	@ResponseBody
	@PostMapping(value="member/detail4"
			   , produces=MediaType.APPLICATION_JSON_VALUE)
	public Member detail4(@RequestBody Member member) {
		return memberService.execute4(member);
	}
	
	
	
	
	
	
	
	
}

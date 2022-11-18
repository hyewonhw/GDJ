package com.gdu.app13.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.service.UserService;

@Component
public class SleepUserCheckingInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 이전에 처리할 것 -> preHandle
		
		// 로그인 하려고 사용자가 입력한 id
		String id = request.getParameter("id");  
		
		// 해당 아이디가 휴면테이블에 있는지 확인
		SleepUserDTO sleepUser = userService.getSleepUserById(id);
		
		// session에 휴면계정 정보를 올려둠
		// 5개 정보(ID, PW, JOIN_DATE, LAST_LOGIN_DATE, SLEEP_DATE)올라가있음
		HttpSession session = request.getSession();
		session.setAttribute("sleepUser", sleepUser);
		
		// 휴면회원이면 복원을 위한 과정(/user/sleep/display)을 진행함 //user/sleep/display => 왜 휴면됐는지 보여주는 화면
		if(sleepUser != null) {
			response.sendRedirect(request.getContextPath() + "/user/sleep/display");  // session에 올려둿기땜시 파라미터 전달 없어도됨
			return false;
		} 
		// 휴면회원이 아니면 로그인(/user/login)을 진행함
		else {
			return true;
		}
		
		
	}
	
}

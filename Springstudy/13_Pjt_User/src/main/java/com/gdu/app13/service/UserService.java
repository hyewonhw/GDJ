package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;

public interface UserService {
	public Map<String, Object> isReduceId(String id);
	public Map<String, Object> isReduceEmail(String email);
	public Map<String, Object> sendAuthCode(String email);
	public void join(HttpServletRequest request, HttpServletResponse response);// 반환타입이 없다는것은 여기서 응답을 만들겠다는 뜻 어디로 넘어갈 필요가 없음
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void keepLogin(HttpServletRequest request, HttpServletResponse response);
	public void logout(HttpServletRequest request, HttpServletResponse response);
	public UserDTO getUserBySessionId(Map<String, Object> map);  // KeepLoginInterceptor에서 호출 원래 controller가 호출하지만 얘는 가로채기때문
	public Map<String, Object> confirmPassword(HttpServletRequest request);
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response);
	public void sleepUserHandle();  // SleepUserScheduler에서 호출
	public SleepUserDTO getSleepUserById(String id);  // 인터셉터에서 부름 인터셉터는 controller login 동작 하려고 할때 실행
	public void restoreUser(HttpServletRequest request, HttpServletResponse response);
	
	public String getNaverLoginApiURL(HttpServletRequest request);  // 네이버로그인-1
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request);  // 네이버로그인-2
}

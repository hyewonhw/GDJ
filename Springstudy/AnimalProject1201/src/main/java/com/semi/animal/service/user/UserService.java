package com.semi.animal.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.animal.domain.user.SleepUserDTO;
import com.semi.animal.domain.user.UserDTO;

public interface UserService {
	
	public Map<String, Object> isReduceId(String id);
	public Map<String, Object> isReduceEmail(String email);
	public Map<String, Object> sendAuthCode(String email);
	public void join(HttpServletRequest request, HttpServletResponse response);
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response);  
	public void keepLogin(HttpServletRequest request, HttpServletResponse response);
	public void logout(HttpServletRequest request, HttpServletResponse response);
	public UserDTO getUserBySessionId(Map<String, Object> map);  // KeepLoginInterceptor에서 호출
	public Map<String, Object> confirmPassword(HttpServletRequest request);
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response);
	public void sleepUserHandle();  // SleepUserScheduler에서 호출
	public SleepUserDTO getSleepUserById(String id);
	public void restoreUser(HttpServletRequest request, HttpServletResponse response);
	public String getNaverLoginApiURL(HttpServletRequest request);  // 네이버로그인-1
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request);  // 네이버 로그인-2

	
}
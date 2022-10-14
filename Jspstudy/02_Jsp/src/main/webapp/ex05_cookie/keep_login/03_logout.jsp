<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// session에 저장된 정보를 초기화하기
	// invalidate() -> unbind함(binding된거 다 풀어버림)
	session.invalidate();
	
	// 쿠키에 저장된 정보를 지우기
	Cookie cookie1 = new Cookie("id", "");
	cookie1.setMaxAge(0);
	response.addCookie(cookie1);
	Cookie cookie2 = new Cookie("pwd", "");
	cookie2.setMaxAge(0);
	response.addCookie(cookie2);
	
	// 로그인 폼으로 돌아가기
	response.sendRedirect("01_form.jsp");
%>

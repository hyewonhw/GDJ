<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에 저장된 cart 제거하기
	// session.invalidate()  -> 전체 삭제
	session.removeAttribute("cart");  // 장바구니만 삭제, 로그인 정보 등은 유지 
	
	// 장바구니 목록으로 이동
	response.sendRedirect("03_cart_list.jsp");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>자유게시판 게시글 상세보기화면</h1>
	
	<div>게시글 번호 ${free.freeNo}</div>
	<div>작성자 ${free.writer}</div>
	<div>작성IP ${free.ip}</div>
	<div>조회수 ${free.hit}</div>
	<div>제목 <input type="text" value="${free.title}"></div>
	<div><textarea>${free.content}</textarea></div>
	<div>
		<input type="button" value="수정" onclick="location.href='${contextPath}/modify.do?freeNo=${free.freeNo}'">
		<input type="button" value="목록" onclick="location.href='${contextPath}/list.do'">
	</div>
	
</body>
</html>
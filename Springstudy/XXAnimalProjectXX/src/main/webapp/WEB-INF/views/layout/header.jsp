<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("Welcome");
	pageContext.setAttribute("title", title);
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/moment-with-locales.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.css">
<style>
	

	#navigation {
		list-style: none;
		
	}
	
	#navigation li {
		display: inline;
		background-color: yellow;
	}
	
	#navigation li a {
		text-decoration: none;
        text-align: center;
	}
	
	
</style>
</head>
<body>
		
	<ul id="navigation">
		<li><a href="${contextPath}">홈으로</a></li>
		<li><a href="${contextPath}/board/login">로그인</a></li>
		<li><a href="${contextPath}/board/free">자유게시판</a></li>
		<li><a href="${contextPath}/gall/list">사진게시판</a></li>
		<li><a href="${contextPath}/board/file">파일게시판</a></li>
		<li><a href="${contextPath}/board/admin">관리자페이지</a></li>
	</ul>
		
	
	

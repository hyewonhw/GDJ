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
	
	<h1>자유게시판 게시글 작성화면</h1>
	<form action="${contextPath}/add.do" method="POST">
		<div>
			<input type="text" name="writer" placeholder="작성자">
		</div>
		<div>
			<input type="text" name="title" placeholder="제목">
		</div>
		<div>
			<textarea name="content" rows="5" cols="30" placeholder="내용"></textarea>
		</div>
		
		<div>
			<input type="submit" value="작성완료" >
			<input type="reset" value="다시작성" >
			<input type="button" value="목록"  onclick="location.href='${contextPath}/list.do'">
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 
		표현언어인 EL이 아닌 자바표현식으로 
		EL로 쓰는거 추천
	-->
	<a href="<%=request.getContextPath()%>/member/manage.do">회원관리</a>

</body>
</html>
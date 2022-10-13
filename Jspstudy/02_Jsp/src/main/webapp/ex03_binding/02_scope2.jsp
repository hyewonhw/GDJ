<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>a : ${a}</div>
	<div>b : ${b}</div>
	<div>c : ${c}</div>
	<div>d : ${d}</div>  <!-- pageContext는 현재페이지(02_scope1.jsp)에서만 유지되기때문에 d의 값은 나오지 않음 -->
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		<form action="${contextPath}/a.do">
			<!-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 -->
			<div>
				<label for="width">너비</label>
				<input type="text" name="width" id="width">
			</div>
			<div>
				<label for="height">높이</label>
				<input type="text" name="height" id="height">			
			</div>
			<div>
				<button name="rectangle">사각형 넓이 확인</button>
				<button name="triangle">삼각형 넓이 확인</button>
			</div>
			<!-- 반지름 입력 폼 : 원 버튼 -->
			<div>
				<label for="radius">반지름</label>
				<input type="text" name="radius" id="radius">
			</div>
				<button name="circle">원 넓이 확인</button>
		</form>
	</div>
	
	

	
</body>
</html>
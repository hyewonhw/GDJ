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

	<h1>여기 예쁜 동물 좀 보세요</h1>
	<img src="${contextPath}/resources/images/animal1.jpg" width="200px">

	<h1>저도 봐 주세요</h1>
	<img alt="" src="${contextPath}/assets/images/animal2.jpg" width="200px">

</body>
</html>
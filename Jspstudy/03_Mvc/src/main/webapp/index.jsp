<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> <%-- value값이 <%=request.getContextPath() 와 동일한 역할을 수행함--%>
<%-- contextPath 변수처리해서 사용 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- welcome파일로 동작하기위해 폴더안에 넣지 않음 -->
	
	<h3><a href="${contextPath}/today.do">오늘은 며칠입니까?</a></h3>    <!--03_Mvc/today.do -->
	<h3><a href="${contextPath}/now.do">지금은 몇시입니까?</a></h3>  	 <!-- 03_Mvc/now.do -->
	<h3><a href="${contextPath}/input.do">입력화면으로 이동하기</a></h3> <!-- 03_Mvc/input.do -->
	
</body>
</html>
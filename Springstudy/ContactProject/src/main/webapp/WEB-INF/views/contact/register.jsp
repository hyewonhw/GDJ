<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#btn_list').click(function(){
			location.href = '${contextPath}/cnt/list';
		});
		
		
		
	});
	
</script>
</head>
<body>

	<h1>연락처 등록</h1>
	<form action="${contextPath}/cnt/register" method="get">
		<div>
			<label for="name">이름* </label>
		</div>
		<div>
			<input type="text" id="name" name="name">
		</div>
		<div>
			<label for="tel">전화* </label>
		</div>
		<div>
			<input type="text" id="tel" name="tel">
		</div>
		<div>
			<label for="addr">주소* </label>
		</div>
		<div>
			<input type="text" id="addr" name="addr">
		</div>
		<div>
			<label for="email">이메일* </label>
		</div>
		<div>
			<input type="text" id="email" name="email">
		</div>
		<div>
			<label for="note">비고</label>
		</div>
		<div>
			<input type="text" id="note" name="note">
		</div>
		<div>
			<button>연락처 저장하기</button>
			<input type="button" value="전체연락처" id="btn_list">
		</div>
	</form>
</body>
</html>
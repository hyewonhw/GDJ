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
</head>
<body>
	
	<h1>예쁜 동물들 보고 가세요</h1>
	
		<!--
			절대 경로의 이미지를 img 태그로 표시하기
			1. 요청
				경로 + 이미지를 파라미터로 전송
			2. 응답
				이미지의 byte[]
		-->
	<div id="galleries"></div>
		<script>
			// 태그 밑에 script짤때는 ready가 필요 없음
			// img태그의 src를 script로 넣어주기
			// 한줄에 하나 나오게 하기위해 div태그사용
			for(let n = 1; n <= 10; n++) {
				$('<div>')
				.append($('<img>')
						.attr('src', '${contextPath}/image/display?path=' + encodeURIComponent('C:\\GDJ\\images') + '&filename=animal' + n + '.jpg')
						.attr('width', '200px'))
				.appendTo('#galleries');
			}
		</script>
	
</body>
</html>
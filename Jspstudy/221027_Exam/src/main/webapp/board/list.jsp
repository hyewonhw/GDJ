<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn_write').click(function(event) {
			location.href = '${contextPath}/board1/write.do';	
		});
	}
</script>
</head>
<body>
	
	<div>
		<table border="1">
			<caption>총 게시글 : ${count}개</caption>
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${count eq 0}">
					<tr>
						<td colspan="4">게시물이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${count ne 0}">
					<c:forEach items="${board}" var="b">
						<tr>
							<td>${b.boardNo}</td>
							<td>${b.writer}</td>
							<td><a href="${contextPath}/board1/detail.do?title=${b.title}">{b.title}</a></td>
							<td>${b.createDate}</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td>
						<button class="btn_write">새글작성</button>
					</td>
				</tr>
			</tbody>
			
		</table>
	</div>
	
</body>
</html>
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

		<table border="1">
			<thead>
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>전화</td>
					<td>주소</td>
					<td>이메일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty contacts}">
					<tr>
						<td colspan="5">없음</td>
					</tr>
				</c:if>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<td>${contact.no}</td>
						<td><a href="${contextPath}/cnt/detail?no=${contact.no}">${contact.name}</a></td>
						<td>${contact.tel}</td>
						<td>${contact.addr}</td>
						<td>${contact.email}</td>
						<td>${contact.note}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5"><a href="${contextPath}/cnt/register">신규 연락처 등록하기</a></td>
				</tr>
			</tfoot>	
		</table>
	
</body>
</html>
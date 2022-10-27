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
		
		$('#frm_write').click(function(event) {
			if($('#title').var() == '') {
				alert('제목은 필수입니다.');
				$('#title').focus();
				event.preventDefault();
				return;
			}
		});
		
		$('#btn_list').click(function(event) {
			location.href = '${contextPath}/board1/list.do';
		});
		
	});
</script>
</head>
<body>
	
	<div>
		<form id="frm_write" action="${contextPath}/board1/add.do">
			<table border="1">
				<tbody>
					<tr>
						<td>작성자</td>
						<td><input type="text" id="writer" name="writer"></td>					
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" id="title" name="title"></td>					
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea id="content" name="content" rows="30" cols="10"></textarea></td>					
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<input type="button" value="등록">
							<input type="button" value="목록" id="btn_list">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	
</body>
</html>
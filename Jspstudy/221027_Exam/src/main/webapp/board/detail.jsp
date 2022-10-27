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
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#btn_remove').click(function(event){
			if(confirm('정말 삭제하시겠습니까?') == false){
				return;
			} else {
				location.href = '${contextPath}/board1/remove.do?boardNo=${board.boardNo}';
				alert('삭제성공');
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board1/list.do';
		});
		
	});

</script>
</head>
<body>
	
	<table>
		<tbody>
			<tr>
				<td>순번</td>
				<td>${board.boardNo}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${board.title}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>{board.content}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td>
					<input type="button" value="수정" id="btn_edit">
					<input type="button" value="목록" id="btn_list">
					<input type="button" value="삭제" id="btn_remove">
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>
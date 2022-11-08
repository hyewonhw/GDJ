<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		
		var frm = $('#frm_btn');
		
		// 편집화면으로 이동
		$('#btn_edit').click(function(){
			frm.attr('action', '${contextPath}/brd/edit');
			frm.submit();
		});
		
		// 삭제(post방식을 사용(get방식을 사용하면 누구나 주소를 알면 삭제할 수 있으므로 주소창에 표시되지 않는 post방식을 사용) : form에 post 메소드 주기)
		$('#btn_remove').click(function(event){		// 서브밋 사용 시, .submit을 사용 -> 편집도 submit를 사용해야하므로 btn-click이벤트로 변경하여 사용해야함.
			if(confirm('삭제할까요?')){
				frm.attr('action', '${contextPath}/brd/remove');
				frm.submit();
				return;
			}
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = '${contextPath}/brd/list';
		});
		
	});
	
</script>
</head>
<body>

	<ul>
		<li>글번호 : ${board.boardNo}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일 : ${board.create_date}</li>
		<li>수정일 : ${board.modify_date}</li>
	</ul>
	<div>
		${board.content}
	</div>
	
	<hr>
	
	<div>
		<form id="frm_btn" method="post">
			<input type="hidden" name="board_no" value="${board.board_no}">		<!-- hidden은 name과 value값을 반드시 기입 / hidden으로 글번호를 주기 않으면 삭제가 동작하지 않음(삭세 시 글번호를 사용하여 삭제하므로) -->
			<input type="button" value="편집" id="btn_edit">
			<input type="button" value="삭제" id="btn_remove">		<!-- action을 동작시키는 submit을 사용(id="btn_remove"를 지우고 form에 id 처리) -> 편집도 동일한 처리를 위해 btn이벤트로 변경-->
			<input type="button" value="목록" id="btn_list">
		</form>
	</div>

</body>
</html>
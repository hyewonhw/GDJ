<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_no}번 게시글</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	// 스크립트가 바디 위에 올라와있을때 
	// 이거 안해주면 못읽음
	$(document).ready(function(){

		$('#btn_edit').click(function(event){
			location.href = '${contextPath}/board/edit.do?board_no=${board.board_no}';
		});
		
		$('#btn_remove').click(function(event) {
			if(confirm('게시글을 삭제할까요?')) {  // 삭제할지 확인함
				location.href = '${contextPath}/board/remove.do?board_no=${board.board_no}';
			} else {
				alert('취소되었습니다.');
			}
		});
		
		$('#btn_list').click(function(event) {
			location.href = '${contextPath}/board/list.do'; 
			// jsp아닌 이유 : write.jsp에서 list.jsp를 부를 수 없음
			// 목록을 보려면 DB를 먼저 실행함(DB에 가는건 list.do)
		});
		
	})

</script>
</head>
<body>

	<h1>게시글 상세 보기</h1>
	<div>
		게시글 번호 : ${board.board_no}
	</div>
	<div>
		게시글 제목 : ${board.title}
	</div>
	<div>
		<pre>${board.content}</pre>
	</div>
	<div>
		작성일자 : ${board.create_date}
	</div>
	<div>
		<input type="button" value="편집" id="btn_edit">
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" id="btn_list">
	</div>
	
	
	
	

</body>
</html>
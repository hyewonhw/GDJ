<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(function(){ 
		fn_list();
		fn_register();
		fn_search();
	});

	function fn_list(){
		$.ajax({
			//요청
			type: 'get',
			url: '${contextPath}/list.json',
			dataType: 'json',
			// 응답
			success: function(resData){
				// 백단필요x 바로 프론트작업
				// 사용을위해 $('#staffList') 비워주기
				$('#staffList').empty();
				// 배열 인덱스 i, 요소 하나하나 staff
				$.each(resData, function(i, staff) {	
					//1. 텍스트 만들기
					var tr = '<tr>';
					tr += '<td>' + staff.sno + '</td>';
					tr += '<td>' + staff.name + '</td>';
					tr += '<td>' + staff.dept + '</td>';
					tr += '<td>' + staff.salary + '</td>';
					tr += '</tr>';
					$('#staffList').append(tr);
					
					
					// 2. jquery
					/* $('<tr>')
					.append($('<td>').text(staff.sno))
					.append($('<td>').text(staff.name))
					.append($('<td>').text(staff.dept))
					.append($('<td>').text(staff.salary))
					.appendTo$('#staffList'); */
				});
			}
		});
	}
	
	function fn_register() {
		$('#btn_register').click(function() {
			// 숫자로 시작해서 숫자로 끝나는 5글자
			if( /^[0-9]{5}$/.test($('#sno').val()) == false) {
				alert('사원번호는 5자리 숫자 입니다.');
				return; // 리턴밑으로 못내려가게 막음 == ajax못하게 막음
				// 서브밋이 아닌 일반 클릭이기때문에 막을 이벤트가 없음
			}
			//부서는 3~5자리 한글로 구성
			if( /^[ㄱ-ㅎㅏ-ㅣ가-힣]{3,5}$/.test($('#dept').val()) == false) {
				alert('부서는 3~5자리 한글입니다.');
				return; 
			}
			$.ajax({
				// 요청
				type: 'post',
				url: '${contextPath}/add',
				data: 'sno=' + $('#sno').val() + '&name=' + $('#name').val() + '&dept=' + $('#dept').val(), 
					 // $('#frm_regist').serialize(),//로 해도됨(name 필요한방식)
				// 응답
				dataType: 'text',
				success: function(resData) {
					// resData에 사원등록 성공했다는 텍스트가 들어있음
					// try에서 응답 만들면 success로 넘어옴
					alert(resData);
					// 밑에 목록에 새로등록된 사람이 나타날 수 있게 fn_list 다시 실행
					fn_list();
					$('#sno').val('')  // document.getElementById('sno').value = '';
					$('#name').val('')
					$('#dept').val('')
				},
				// exception받아줄 error가 필요
				error: function(jqXHR) {
					// catch에서 응답 만들면 error로 넘어옴
					alert(jqXHR.responseText);
				}
			});
		});
	}
	
	function fn_search(){
		$('#frm_search').submit(function(event){
			if($('#sno').val() == ''){
				alert('조회된 사원 정보가 없습니다');
				event.preventDefault();  // 서브밋 취소
				return;  // 더 이상 코드 실행할 필요 없음
			}
			$.ajax({
				// 요청
				type:"get",
				url: "${contextPath}/get.json",
				data: param,
				// 응답
				dataType: 'json',
				success: function(resData){
					$('#staffList').empty();
					$.each(resData, function(i, staff) {	
						$('<tr>')
						.append($('<td>').text(staff.sno))
						.append($('<td>').text(staff.name))
						.append($('<td>').text(staff.dept))
						.append($('<td>').text(staff.salary))
						.appendTo$('#staffList');
					});
				},
				error: function(jqXHR) {
					alert('조회된 사원 정보가 없습니다.');
				}
			});
		});
	}
	
	/* $('#frm_search').submit(function(event){
		if($('#title').val() == ''){
			alert('조회된 사원 정보가 없습니다');
			event.preventDefault();  // 서브밋 취소
			return;  // 더 이상 코드 실행할 필요 없음
		}
	}); */
	
</script>
</head>
<body>

	<h3>사원등록</h3>
	<form id="frm_register">
		<input type="text" id="sno" name="sno" placeholder="사원번호">
		<input type="text" id="name" name="name" placeholder="사원명">
		<input type="text" id="dept" name="dept" placeholder="부서명">
		<input type="button" value="등록" id="btn_register">
	</form>
	
	<hr>

	<h3>사원조회</h3>
	<form id="frm_search">
		<input type="text" id="search" name="search" placeholder="사원번호입력">
		<input type="submit" value="조회" id="btn_find">
		<input type="button" value="전체" onclick="location.href='${contextPath}'" >
	</form>

	<hr>

	<h3>사원목록</h3>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>연봉</td>
			</tr>
		</thead>
		<tbody id="staffList">
			
		</tbody>
	</table>

</body>
</html>
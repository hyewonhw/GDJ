<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	pageContext.setAttribute("contextPath", contextPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	/*
	자바 스크립트 코드
	onload = function() {}
	*/
	/*
	jquery 코드
	반드시 이벤트 ready를 가져야함
	여기서 요청하고 요청을 받아옴 데이터(JSON) 자체가 응답으로 넘어옴
	*/ 
	$(document).ready(function(event){
		fn_getAllMembers();
		fn_getMember();
		fn_registration();
	});
	
	
	function fn_getAllMembers() {
		$.ajax({
			/* 요청 */
			type: 'get',
			url: '${contextPath}/member/list.do',
			/* 응답 */
			dataType: 'json',
			success: function(resData){  // resData: {"count": 3, "member": [{},{},{}]} 객체 프로퍼티 뽑아내기
				// 1. resData.count 또는 resData['count']
				$('#count').text(resData.count);
				// 2. member_list 영역 초기화	
				$('#member_list').empty();
				// 3. resData.members : [{}, {}, {}]
				// 	  $.each(배열, function(인덱스, 배열요소){})
				$.each(resData.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M'? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>';  
					tr += '<td><input type="hidden" value="' + member.memberNo + '"><input type="button" value="조회" class="btn_detail"></td>';  // 첫번째 input을 this로 호출 / 두번째 input은 this.next() / input위치바뀌면 prev()
					tr += '</tr>';
					$('#member_list').append(tr); // 태그라서 text불가능 / JS의innerHTML임 
					// html은 원래있던거 덮어쓰는거라 append사용해야함 
				})
			}
		});	
	}
	
	
	function fn_getMember(){
		// 조회버튼은 동적 요소이기 때문에 다음 이벤트 방식을 사용해야한다
		// 동적요소를 품고있는 부모요소가 먼저 나옴(바로 윗 부모 아니여도됨 부모면됨)
		// $('부모요소').on(이벤트타입, 이벤트대상, 이벤트리스너)
		$('body').on('click','.btn_detail', function(){
			$.ajax({
				/* 요청 */
				type:"get",
				url: "${contextPath}/member/detail.do",	   // 요청 주소
				data: 'memberNo=' + $(this).prev().val(),  // 요청 파라미터
				/* 응답 */
				dataType: 'json',
				success: function(resData){	// resData : {"exists": true, "member": {}}
					if(resData.exists) {
						alert('회원 정보가 조회되었습니다.');
						$('#id').val(resData.member.id).prop('readonly', true); // 수정불가능하게 readonly	  
						$('#name').val(resData.member.name);
						$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true); // .val()로 하면 value를 바꾼다는 의미
						// $(':radio[name=gender]').val(M).prop('checked', true); // 남자 여자 다 M으로 바꿔버린다는 의미
						$('#grade').val(resData.member.grade);  // db에서 가져온 데이터가 option의 value와 같아야함
						$('#address').val(resData.member.address);
					} else {
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			})
		})
	}
	
	
	function fn_registration() {
		
		$('#btn_add').click(function() {
			
			$.ajax({
				/* 요청 */
				type: 'post',
				url: '${contextPath}/member/add.do',
				data: $('#frm_member').serialize(),  // serialize() : form내부 모든 입력 요소를 파라미터로 변환해줌, name속성을 파라미터로
				/* 응답 */
				dataType:'json',
				// 정상 응답
				success: function(resData){  // resData : {"isSuccess": true}
					if(resData.isSuccess) {
						alert('신규 회원이 등록되었습니다.');
						fn_getAllMembers();  // 등록 후 목록 갱신 필요
					} else {
						alert('신규 회원 등록이 실패했습니다.');
					}
				},
				// 예외 응답
				error: function(jqXHR) {  // 예외 처리 응답 데이터(일반 텍스트)는 jqXHR 객체의 responseText속성에 저장됨.
					alert(jqXHR.responseText);
				}
			});  // ajax
			
		});  // click
		
	} // function
	
	
	
	
	
	
	
	
	
	
	
	
</script>
</head>
<body>
	
	<div class="wrap">
		<h1 class="title">회원관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>	
				<input type="text" id="id" name="id">
			</div>
			<div>
				<label for="name">이름</label>	
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="male">남자</label>
				<input type="radio" id="male" name="gender" value="M">
				<label for="female">여자</label>
				<input type="radio" id="female" name="gender" value="F">
			</div>
			<div>
				<label for="grade">회원등급</label>
				<select id="grade" name="grade">
					<option value="">등급선택</option>
					<option value="gold">골드</option>
					<option value="silver">실버</option>
					<option value="bronze">브론즈</option>
				</select>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="button" value="최기화" id="btn_init">
				<input type="button" value="신규등록" id="btn_add">
				<input type="button" value="변경내용저장" id="btn_modify">
				<input type="button" value="회원삭제" id="btn_remove">
			</div>
		</form>
		<hr>
		<table class="member_table">
			<caption>전체 회원수 : <span id="count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>성명</td>
					<td>성별</td>
					<td>등급</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
		
	</div>
	
</body>
</html>
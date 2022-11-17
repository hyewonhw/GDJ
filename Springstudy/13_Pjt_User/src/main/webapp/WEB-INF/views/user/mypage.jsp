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
<script type="text/javascript">
	
	$(function() {
		fn_showHide();
		fn_init();
		fn_pwCheck();
		fn_pwCheckAgain();
		fn_pwSubmit();
	});
	
	var pwPass = false;
	var rePwPass = false;
	
	function fn_showHide(){
		
		// 평소엔 안보이게해놓고
		$('#modify_pw_area').hide();
		
		// 버튼누르면 보이게하기
		$('#btn_edit_pw').click(function(){
			//초기화
			fn_init();
			// 보이게하기
			$('#modify_pw_area').show();
		});
		
		// 취소버튼
		$('#btn_edit_pw_cancel').click(function(){
			// 초기화
			fn_init();
			// 숨기기
			$('#modify_pw_area').hide();
		});
	}
	
	function fn_init() {
		$('#pw').val('');
		$('#re_pw').val('');
		$('#msg_pw').text('');
		$('#msg_re_pw').text('');
	}
	
	function fn_pwCheck(){
		
		$('#pw').keyup(function(){
		
			let pwValue = $(this).val();
		
			let regPw = /^[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			
			let validatePw = /[0-9]/.test(pwValue)
			                 + /[a-z]/.test(pwValue)
			                 + /[A-Z]/.test(pwValue)
			                 + /[!@#$%^&*]/.test(pwValue);
         
			if(regPw.test(pwValue) == false || validatePw < 3){
				$('#msg_pw').text('8~20자의 소문자, 대문자, 숫자, 특수문자(!@#$%^&*)를 3개 이상 조합해야 합니다.');
				pwPass = false;
			} else {
				$('#msg_pw').text('사용 가능한 비밀번호입니다.');
				pwPass = true;
			}
		
		}); // keyup
		
	} // fn_pwCheck
	
	function fn_pwCheckAgain() {
		
		$('#re_pw').keyup(function(){

			let rePwValue = $(this).val();
			
			if(rePwValue != '' && rePwValue != $('#pw').val()){
				$('#msg_re_pw').text('비밀번호를 확인하세요.');
				rePwPass = false;
			} else {
				$('#msg_re_pw').text('');
				rePwPass = true;
			}
			
		});  // keyup
		
	}  // fn_pwCheckAgain
	
	function fn_pwSubmit() {
		
		$('#frm_edit_pw').submit(function(){
			
			if(pwPass == false || rePwPass == false) {
				alert('비밀번호 입력을 확인하세요.');
				event.preventDefault();
				return;
			}
			
		});
		
	}
	
	
	
	
</script>

</head>
<body>

	<div>
		
		<h1>마이페이지</h1>
		
		<div>
			<input type="button" value="비밀번호변경" id="btn_edit_pw">
		</div>
		<div id="modify_pw_area">
			<form id="frm_edit_pw" action="${contextPath}/user/modify/pw" method="post">
				<!--패스워드 -->
				<div>
					<label for="pw">비밀번호</label>
					<input type="password" name="pw" id="pw" >
					<span id="msg_pw"></span>
				</div>
				
				<!--패스워드 재확인 -->
				<div>
					<label for="re_pw">비밀번호 확인</label>
					<input type="password" id="re_pw" >
					<span id="msg_re_pw"></span>
				</div>
				<div>
					<button>비밀번호 변경하기</button>
					<input type="button" value="취소하기" id="btn_edit_pw_cancel">
				</div>
			</form>
		</div>
		
	</div>

</body>
</html>
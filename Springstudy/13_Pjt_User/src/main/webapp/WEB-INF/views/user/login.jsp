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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	/*
		jsessionid의 value(식별자로 저장됨)
		cookie에 들어간 sessionid값과 사용자의 DB에 저장된 sessionid값을 비교
		--> 같으면 로그인유지 체크한 사람인걸로 인식함 
		사용자를 식별하는 것이 아닌 쿠키로 식별함
	*/
	
	
	$(function(){
		
		fn_login();
		fn_displayRememberId();
		
	});
	
	function fn_login(){
		
		$('#frm_login').submit(function(event) {
			
			if($('#id').val() == '' || $('#pw').val() == '') {
				alurt('아이디와 패스워드를 모두 입력하세요.');
				event.preventDefault(); // submit막기
				return;  				// 아래 if문 막기
			}
			
			// 아이디 저장
			if($('#rememberId').is(':checked')){
				$.cookie('rememberId', $('#id').val());				
			} else {
				$.cookie('rememberId', '');
			}
			
		});
		
	}  // fn_login
	
	function fn_displayRememberId() {
		
		let rememberId = $.cookie('rememberId');
		if(rememberId == ''){
			$('#id').val('');
			$('#rememberId').prop('checked', false);  // 체크해제
		} else {
			$('#id').val(rememberId);
			$('#rememberId').prop('checked', true);  
		}
		
	}
	
	
	
</script>
</head>
<body>

	<div>
	
		<h1>로그인</h1>
		
		<form id="frm_login" action="${contextPath}/user/login" method="post">
			
			<input type="hidden" name="url" value="${url}">
			
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id">
			</div>
			
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
			</div>
			
			<div>			
				<button>로그인</button>
			</div>
			
			<div>			
				<label for="rememberId">
					<input type="checkbox" id="rememberId">
					아이디 저장
				</label>
				<label for="keepLogin">
					<input type="checkbox" name="keepLogin" id="keepLogin">
					로그인 유지
				</label>
			</div>
		
		</form>
			
		<div>
			<a href="${contextPath}/user/findId">아이디 찾기</a> | 
			<a href="${contextPath}/user/findPw">비밀번호 찾기</a>
		</div>
		
		<hr>
	
		<div>
			<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
		</div>
	
	</div>
	
</body>
</html>
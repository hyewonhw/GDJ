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
</head>
<body>
	<!-- session에 올라가있는거 EL로 꺼내쓸수있음 -->
	
	<!-- 로그인이 안 된 상태  -->
	<c:if test="${loginUser == null}">
		<a href="${contextPath}/user/agree">회원가입페이지</a>	
		<a href="${contextPath}/user/login/form">로그인</a>	
	</c:if>

	<!-- 로그인이 된 상태  -->
	<c:if test="${loginUser != null}">
		<div>
			<a href="${contextPath}/user/mypage">${loginUser.name}</a> 님 반갑습니다.
		</div>
		<a href="${contextPath}/user/logout">로그아웃</a>
		
		<a id="lnk_retire" href="${contextPath}/user/retire">회원탈퇴</a>
		<script>
			$('#lnk_retire').click(function(){
				if(confirm('탈퇴하시겠습니까?') == false){
					event.preventDefault();  // a 태그의 기본이벤트(href 속성) 실행을 막음
					return;
				}
			});
		</script>
	</c:if>

</body>
</html>
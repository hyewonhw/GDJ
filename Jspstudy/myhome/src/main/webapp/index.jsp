<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.1.min.js"
  integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
  crossorigin="anonymous">/* jquery 준비안됐을때 사용가능한 방법 */</script>
</head>
<body>
	
	<c:if test="${login == null}">
		<div>
			<form method="post" action="${contextPath}/member/login.me">
				<div>
					<input type="text" name="id" placeholder="아이디">
				</div>
				<div>
					<input type="password" name="pw" placeholder="패스워드">
				</div>
				<div>
					<button>로그인</button>
				</div>
				<div>
					<a href="${contextPath}/member/join.me">회원가입</a>
				</div>
			</form>
		</div>
	</c:if>

	<c:if test="${login != null}">
		<div>
			${login.name}님 어서오세요
			<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.me';">
		</div>
		<div>
			<a id="cancel_link" href="${contextPath}/member/cancel.me">회원탈퇴</a>
			<script>
				$('#cancel_link').click(function(event) {
					if(confirm('탈퇴하시겠습니까?') == false) {
						// 코드의 진행을 막는건 return
						// a링크의 기본 default동작을 막는건 preventDefault()
						event.preventDefault(); 		// a태그의 기본 동작인 href로 이동하는걸 막는다.
						return
					}
				});
			</script>
		</div>
	</c:if>






















</body>
</html>
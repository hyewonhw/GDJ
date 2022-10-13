<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	label {
		display: block;
	}
	label span {
		display: inline-block;
		width: 100px;
	}
</style>
</head>
<body>

	<!-- 
		1) 자바로 today를 구함
		2) EL사용할수있는 영역 (4개의 저장영역에 바인딩되어있어야 EL사용 가능)
	 -->
	<%
		Date date = new Date();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		// today의 EL 사용을 위해서 속성으로 저장(today는 자바변수이기 때문에 EL 사용 불가능)
		pageContext.setAttribute("today", today);
	%>
		
	<div>
		<form method="POST" action="<%=request.getContextPath()%>/ex03_binding/03_qna2.jsp">  <%-- <%=request.getContextPath()%>가 02_Jsp임 --%>
			<label for="created_date">
				<span>작성일</span>
				<input type="text" name="created_date" id="created_date" value="${today}">  <!-- 오늘 날짜가 자동으로 입력됨 -->
			</label>
			<label for="writer">
				<span>작성자</span>
				<input type="text" name="writer" id="writer">
			</label>
			<label for="title">
				<span>제목</span>
				<input type="text" name="title" id="title">
			</label>
			<label for="content">
				<span>내용</span>
				<input type="text" name="content" id="content">
			</label>
			<button>문의 남기기</button>
			<input type="reset" value="다시작성">
		</form>
	</div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	* {
		box-sizing: booder-box;
	}
	a {
		text-decoration: none;
		color:gray;
	}
	.paging {
		width: 210px;
		margin: 0 auto;
		color: gray;
	}
	.paging a, .paging span {
		display: inline-block;
		width: 30px;
		height: 30px;
		line-height: 30px;
		text-align: center;
	}
	.hidden {
		visibility: hidden;
	}
	.now_page {
		border: 1px solid gray;
		color: teal;
		font-weight: 900;
	}
	.lnk:hover {
		border: 1px solid gray;
		color: skyblue;
	}
	
</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// area1, area2 표시
		// 초기 상태 : area1, area2 둘 다 숨김
		$('#area1, #area2').css('display', 'none');
		// column 선택에 따른 area1, area2 표시
		$('#column').change(function(){
			let combo = $(this);
			if(combo.val() == '') {
				$('#area1, #area2').css('display', 'none');
			} else if(combo.val() == 'HIRE_DATE' || combo.val() == 'SALARY') {
				$('#area1').css('display', 'none');	
				$('#area2').css('display', 'inline');	
			} else {
				$('#area1').css('display', 'inline');	
				$('#area2').css('display', 'none');	
			}
		});
		
		// 자동완성
		$('#email').keyup(function(){
			$('#auto_complete').empty();
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/emp/autoComplete',
				data: 'param=' + $(this).val(),
				/* 응답 */
				dataType: 'json',
				success: function(resData){
					// $.each(배열, function(인덱스, 요소){} -> 요소는 그냥 이름 붙여줌
					// autocomplete안에 option집어넣는걸 jquery로 함
					if(resData.status == 200) { // 데이터가 들어있다면
						$.each(resData.list, function(i, emp){
							$('#auto_complete')
							.append($('<option').val(emp["email"]));  // 또는val(emp.email)
						});
					}
				}
			});
		});
		
	});
</script>
</head>
<body>

	<div>
		<form id="frm_search" action="${contextPath}/emp/search">
			<select id="column" name="column">
				<option value="">:::선택:::</option>
				<option value="EMPLOYEE_ID">사원번호</option>
				<option value="E.DEPARTMENT_ID">부서번호</option>
				<!------------------------------------------------완벽일치-->
				<option value="LAST_NAME">성</option>
				<option value="FIRST_NAME">이름</option>
				<option value="PHONE_NUMBER">연락처</option>
				<!------------------------------------------------일부만일치-->
				<option value="HIRE_DATE">입사일</option>
				<option value="SALARY">연봉</option>
				<!------------------------------------------------범위로조회-->
			</select>
			<span id="area1">
				<input type="text" id="query" name="query">
			</span>
			<span id="area2">
				<input type="text" id="start" name="start">
				~
				<input type="text" id="stop" name="stop">
			</span>
			<span>
				<input type="submit" value="검색">
				<input type="button" value="전체사원조회" id="btn_all">
			</span>
		</form>
	</div>
	
	<div>
		<label for="email">이메일</label>
		<input type="text" id="email" name="email" list="auto_complete">
		<datalist id="auto_complete"></datalist>
	</div>
	
	<hr>

	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName} ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>
						<td>${emp.deptDTO.departmentId}</td>
						<td>${emp.deptDTO.departmentName}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>
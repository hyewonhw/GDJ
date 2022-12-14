<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그수정" name="title"/>
</jsp:include>

<script>
	
	// contextPath를 반환하는 자바스크립트 함수
	function getContextPath() {
		var begin = location.href.indexOf(location.origin) + location.origin.length;
		var end = location.href.indexOf("/", begin + 1);
		return location.href.substring(begin, end);
	}
	
	$(document).ready(function(){
		
		// summernote
		$('#content').summernote({
			width: 800,
			height: 400,
			lang: 'ko-KR',
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']]
			],
			callbacks: {
				onImageUpload: function(files){
					for(let i = 0; i < files.length; i++) {
						var formData = new FormData();
						formData.append('file', files[i]);
						$.ajax({
							type: 'post',
							url: getContextPath() + '/blog/uploadImage',
							data: formData,
							contentType: false,
							processData: false,
							dataType: 'json',
							success: function(resData){
								$('#content').summernote('insertImage', resData.src);
							}
						});
					}
				}
			}
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = getContextPath() + '/blog/list';
		});
		
		// 서브밋
		$('#frm_edit').submit(function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				event.preventDefault();
				return;
			}
		});
		
	});
	
</script>


<div>

	<h1>작성 화면</h1>
	
	<form id="frm_edit" action="${contextPath}/blog/modify" method="post">
	
		<input type="hidden" name="blogNo" value="${blog.blogNo}">
	
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title" value="${blog.title}">
		</div>
		
		<div>
			<label for="content">내용</label>
			<textarea name="content" id="content">${blog.content}</textarea>				
		</div>
		
		<!-- 써머노트에서 사용한 이미지 목록(등록 후 삭제한 이미지도 우선은 모두 올라감: 서비스단에서 지움) -->
		<div id="summernote_image_list"></div>
		
		<div>
			<button>수정완료</button>
			<input type="reset" value="작성초기화">
			<input type="button" value="목록" id="btn_list">
		</div>
		
	</form>
	
</div>

</body>
</html>
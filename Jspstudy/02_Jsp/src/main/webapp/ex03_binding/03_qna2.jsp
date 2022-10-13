<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	// 이렇게 하면 그냥 Servlet
	request.setCharacterEncoding("UTF-8");
	String createdDate = request.getParameter("created_date");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 작성자 IP
	String ip = request.getRemoteAddr();
	
	// 파일명
	// 작성자IP_작성자.txt
	// IPv6로 받아오면 콜론(:)포함 -> :을 _로 바꿔주기
	// IPv4로 받아오면 (.)포함 -> .을 _로 바꿔주기 ("\\." , "[.]")
	
	// IPv4
	// 127.0.0.1
	// 127_0_0_1 replaceAll("\\.", "_")
	
	// IPv6
	// 0:0:0:0:0:0:0:1
	// 0_0_0_0_0_0_0_1  replaceAll(":", "_")
	String filename = ip.replaceAll(":", "_").replaceAll("\\.", "_") + "_" + writer + ".txt";
	
	// 파일 경로
	// 파일 경로 -> 리눅스(/) = 윈도우(\) -> 알아서 가져다 쓰게 File.separator사용
	String realPath = application.getRealPath("storage" + File.separator + createdDate);
	File dir = new File(realPath);
	if(dir.exists() == false) {
		dir.mkdirs();
	}
	
	// 생성할 파일 객체
	File file = new File(dir, filename);
	
	// 문자 출력 스트림 생성
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성일자 : " + createdDate + "\n");
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : " + title + "\n");
	bw.write("내용\n" + content + "\n");
	bw.close();
%>    
    
<script>
	var isCreated = <%=file.exists()%>;
	if(isCreated) {
		alert('<%=filename%> 파일이 생성되었습니다.');  <%-- <%=file.getName()%> 사용가능 --%>
	} else {
		alert('파일이 생성되지 않았습니다.');
	}
	history.back();  // 뒤로 이동(작성 페이지로 돌아가기)
</script>
    
    
    
    
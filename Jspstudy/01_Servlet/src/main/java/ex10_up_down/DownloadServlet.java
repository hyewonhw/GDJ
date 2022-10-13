package ex10_up_down;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadServlet")


public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");

		// 다운로드할 파일 경로
		String realPath = getServletContext().getRealPath("upload");
		
		// 다운로드할 파일 객체
		File file = new File(realPath, filename);
		
		// 다운로드할 파일을 읽어 들일 바이트 기반 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드 응답 헤더
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));  // URLEncoder.encode(filename, "UTF-8") -> 파일명 한글깨져서 인코딩해줌
		response.setHeader("Content-Length", file.length() + "");  // file.length()롱타입임 + "" String으로 바꿔쥐위해 빈문자열
		
		// 응답으로 내 보낼 바이트 기반 출력 스트림 
		// -> 문자 내보낼거아니기 때문에 response.getOutputStream() 사용함
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사
		byte[] b = new byte[1024];
		int readByte = 0;   // -> 실제로 읽어들인 byte를 저장(b보다 작을수있기때문)
		while((readByte = in.read(b)) != -1) {
			out.write(b, 0, readByte); // -> 인덱스0번부터 실제로 읽어들인만큼만 복사
		}
		
		out.close();
		in.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

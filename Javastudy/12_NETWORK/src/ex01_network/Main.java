package ex01_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {

	public static void m1() {		// URL
		
		// URL
		// 1. Uniform Resource Locator
		// 2. 정형화된 자원의 경로
		// 3. 웹 주소를 의미
		// 4. 구성
		//  프로토콜: //호스트(:포트번호)/  서버경로  ?  파라미터=값&파라미터=값
		//	   https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EB%82%A0%EC%94%A8
		//    1) https : secure http, 하이퍼텍스트 전송 프로토콜(통신규약)
		//    2) 호스트 : 서버주소
		//    3) 서버경로 : URL Mapping
		//    4) 파라미터 : 서버로 전송하는 데이터
		
		try {
		
			// URL 처리를 위한 URL 클래스
			String apiURL = "https://search.naver.com/search.naver?query=날씨";
			URL url = new URL(apiURL);
			
			// URL 확인
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost());
			System.out.println("파라미터 : " + url.getQuery());
			
		} catch(MalformedURLException e) {		// 형식이 잘못됐다는 예외처리
			System.out.println("API 주소 오류");
		}
	
	}
	
	public static void m2() {		// HttpURLConnection + 응답 코드
		
		// HttpURLConnection 클래스
		// 1. 웹 접속을 담당하는 클래스
		// 2. URL 클래스와 함께 사용
		// 3. URLConnection 클래스의 자식 클래스
		// 4. URL클래스의 openConnertion()메소드를 호출해서 HttpURLConnection 클래스의 타입으로 저장
		//  openConnertion()메소드를 호출 -> URLConnection -> HttpURLConnection에 저장
		try {
			
			String apiURL = "https://www.google.com";
			URL url = new URL(apiURL);
			
			//HttpURLConnection con = url.openConnection();		// 다운캐스팅 필요
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// HTTP 응답 코드
			// 1. 200 : 정상
			// 2. 40X : 요청이 잘못됨 (사용자 잘못)
			// 3. 50X : 서버 오류 (개발자가 코드 잘못짠거)
			
			System.out.println("응답 코드 : " + con.getResponseCode());		// 응답
			System.out.println("정상 : " + HttpURLConnection.HTTP_OK);		// 접속에 성공했는지 여부 알수 있음 200 떨어지면 접속성공
			System.out.println("Not Found : " + HttpURLConnection.HTTP_NOT_FOUND);
			System.out.println("Internal Error : " + HttpURLConnection.HTTP_INTERNAL_ERROR);			
			System.out.println("컨텐트 타입 : " + con.getContentType());
			System.out.println("요청 방식 : " + con.getRequestMethod());	// 요청
			
			// 접속 끊기(생략 가능)
			con.disconnect();
			
		} catch (MalformedURLException e) {		// URL 클래스 때문
			System.out.println("API 주소 오류");
		} catch (IOException e) {		// HttpURLConnection클래스 때문
			System.out.println("API 접속 오류");
		}
		
	}
	
	public static void m3() {		// HttpURLConnection과 Stream 	(암기)
		
		// HttpURLConnection과 스트림
		
		try {
			String apiURL = "http://www.naver.com";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 바이트 입력 스트림
			InputStream in = con.getInputStream(); 		
			
			// 문자 입력 스트림으로 변환
			InputStreamReader reader = new InputStreamReader(in);		
		
			
			// 모두 읽어서 StringBuilder에 저장
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[100];		// 100글자씩 처리
			int readCnt = 0;		// 실제로 읽은 글자 수
			
			while((readCnt = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
				
			}
			
			// StringBuilder의 모든 내용을 C:\\storage\\naver.html로 내보내기
			File file = new File("C:\\storage", "naver.html");
			FileWriter fw = new FileWriter(file);
			
			fw.write(sb.toString());
			
			fw.close();
			reader.close();
			con.disconnect();
		
		} catch (MalformedURLException e) {
			System.out.println("API 주소오류");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("API 접속 및 연결 오류");
		}

	}
	
	public static void m4() {		// encoding + decoding
		
		// 인코딩 : UTF-8 방식으로 암호화
		// 디코딩 : UTF-8 방식으로 복호화
		// 원본데이터 -> 인코딩 -> 전송 -> 디코딩 -> 원본데이터
		
		try {
			
			// 원본데이터
			String str = "한글 english 12345 !@#$+";
			// 출력 -> %ED%95%9C%EA%B8%80+english+12345+%21%40%23%24%2B
			// 공백은 +로 나옴
			
			// 인코딩
			String encode = URLEncoder.encode(str, "UTF-8");	// 인코드할거, 인코드방식
			System.out.println(encode);
			
			// 디코딩
			String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8); 		// 원본이아닌 인코드 데이터를 디코딩			
			System.out.println(decode);
			
		} catch(UnsupportedEncodingException e) {		// UTF를 UFT 라고 적는 등 오타
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		m4();
	}

}

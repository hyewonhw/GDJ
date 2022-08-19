package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main1_XML {
	
	// 요청  
	// 1. Request
	// 2. 클라이언트가 서버측으로 보내는 데이터	(반대는 응답)
	
	// 파라미터
	// 1. Parameter
	// 2. 보내는 데이터(변수 개념)
	
	// 응답 
	// 1. Response
	// 2. 서버가 클라이언트에게 보내는 데이터
	
	
	public static void m1() {
		
		// 전국종량제봉투가격표준데이터
		
		// API 주소 (주소 + 요청 파라미터)
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";
		String serviceKey = "PBnvMtbLwyfGOMgTVqqScsmhW6PMGPUJbkf8VRdREZGmOdhlp43N64ogyLPUrByDX8elpaLkHr5c1d40oqja0g==";
		
		try {
			
			// 활용신청한 데이터의 Decoding키 
			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");
			apiURL +="&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL +="&type=" + URLEncoder.encode("xml", "UTF-8");
			apiURL +="&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL +="&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL +="$WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL +="$WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL +="$WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL +="$WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL +="&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
			
		} catch(UnsupportedEncodingException e) {		// 지원되지않는 인코딩
			System.out.println("인코딩 실패");
		}
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();		// 캐스팅해주고
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		} catch(MalformedURLException e) {				// 잘못된 URL주소
			System.out.println("API 주소 오류");
		} catch (IOException e) {						// In/Out오류
			System.out.println("API 접속 실패");
		}
		
		// 입력 스트림(응답) 생성
		// 1. 응답 성공 시 정상 스트림 사용, 실패 시 에러 스트림 사용
		// 2. 응답 데이터는 StringBuilder에 저장
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));		// 정상스트림
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));		// 에러스트림
			}
			
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line + "\n");
			} 
			// 스트링 종료
			reader.close();
		
		} catch (IOException e ) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 xml 데이터
		String response = sb.toString();
	
		//  File생성
		File file = new File("C:\\storage", "api1.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		// 접속 종료
		con.disconnect();
	}
	public static void m2() {	
		
		// XML File 생성
		File file = new File("C:\\storage", "api1.xml");
			
		// xml 분석
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();		// <response>	(-> 최상위 태그)
			System.out.println(root.getNodeName());
			
			NodeList nodeList = root.getChildNodes();		// <response>의 자식 태그 (<header>, <body>)
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); 				// node가 <header>와 <body>임
				System.out.println("  " + node.getNodeName());
				NodeList nodeList2 = node.getChildNodes();		// <header>의 자식 태그(<resultCode>, <resultMsg>), <body>의 자식태그(<items>, <numOfRows>, <pageNo>, <totalCount>)
				for(int j = 0; j < nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					System.out.println("    " + node2.getNodeName());
					if(node2.getNodeName().equals("items")) {		// <item> 태그 대상 
						NodeList items = node2.getChildNodes();		// <item>의 자식 태그(<item>)
						for(int k = 0; k < items.getLength(); k++) {
							Node item = items.item(k);
							System.out.println("      " + item.getNodeName());
							NodeList itemChildren = item.getChildNodes();		// <item>의 자식 태그
							for(int l = 0; l < itemChildren.getLength(); l++) {
								Node itemChild = itemChildren.item(l);
								System.out.println("        " + itemChild.getNodeName() + ":" + itemChild.getTextContent());
							}
						}
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void m3() {
		
		// 보건복지부_코로나 19 감염현황 조회 서비스
		
		// 인증키
		String serviceKey = "PBnvMtbLwyfGOMgTVqqScsmhW6PMGPUJbkf8VRdREZGmOdhlp43N64ogyLPUrByDX8elpaLkHr5c1d40oqja0g==";
		
		// API 주소 (주소 + 요청 파라미터)
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			
			urlBuilder.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));		
			urlBuilder.append("&startCreateDt=20220808");
			urlBuilder.append("&endCreateDt=20220812");
			
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL= urlBuilder.toString();
	
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
	
		try {
			url = new URL(apiURL);
			con  = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 접속 실패");
		}
		// 입력 스트림 생성
		// 1. 서버가 보낸 데이터를 읽어야 하므로 입력 스트림이 필요
		// 2. 서버와 연결된 입력 스트림은 바이트 스트림이르모 문자 스트림으로 변환해야 함
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			// 스트림 종료
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 xml대이터
		String response = sb.toString();
		
		// XML File 생성
		File file = new File("C:\\storage", "api2.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void m4() {
		
		// xml파싱
		
		File file  = new File("C:\\storage", "api2.xml"); 
		
		try {
			
			// api2.xml 문서 -> doc 객체
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);		// api2.xml 파일 전체를 doc이라고 부름 ----> 고로 doc에는 다 들어있음
			
			// api2.xml 문서의 최상위 태그 -> root
			Element root = doc.getDocumentElement(); 	// 문서에 포함된 최상위태그(response)를 가져와라
			
			// <item>...</item> 태그 하나 == 특정 날짜의 데이터---------->5개
			StringBuilder sb = new StringBuilder();
			NodeList items = root.getElementsByTagName("item"); 	// 태그 이름으로 요소찾기
			for(int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i); 	// 노드이름에서 노드하나 가져옴
				NodeList itemChildren = item.getChildNodes(); 	// item의 자식들 다 데리고옴
				for(int j = 0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j); 		// 모든자식들이기째문에 엔터도 포함되어있고 그럼
					if(itemChild.getNodeName().equals("stateDt")) { 	
						sb.append(" 날짜 : ").append(itemChild.getTextContent());
					}
					else if(itemChild.getNodeName().equals("decideCnt")) {
						sb.append(" 확진자수 : ").append(itemChild.getNodeName());
					}
					else if(itemChild.getNodeName().equals("deathCnt")) {
						sb.append(" 사망자수 : ").append(itemChild.getTextContent());
					}
				} 
				sb.append("\n"); 
				
				// Node stateDt = itemChildren.item(4);	// 5번째 노드
				// Node stateDt 			== <stateDt>20220812</stateDt>
				// stateDt.getNodeName() 	== stateDt(태그이름)
				// stateDt.getTextContent() == 20220812(태그내부텍스트)
			}
			System.out.println(sb.toString());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m5() {
		
		// 기상청 단기예보 조회서비스
		
		// 인증키
		String serviceKey = "PBnvMtbLwyfGOMgTVqqScsmhW6PMGPUJbkf8VRdREZGmOdhlp43N64ogyLPUrByDX8elpaLkHr5c1d40oqja0g==";
		
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			urlBuilder.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&numOfRows=");
			urlBuilder.append("&dataType=XML");
			urlBuilder.append("pageNo=");
			urlBuilder.append("&base_date=20220818");
			urlBuilder.append("&base_time=0600");
			urlBuilder.append("&nx=55");	// 서울특별시 금천구 가산동 좌표x
			urlBuilder.append("&ny=127");	// 서울특별시 금천구 가산동 좌표y
			
			
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL= urlBuilder.toString();

		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset-UTF-8");
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 접속 실패");
		}
	
		// 입력 스트림 생성 
		// 1. 서버가 보낸 데이터를 읽어야 하므로 입력 스트림이 필요
		// 2. 서버와 연결된 입력 스트림은 바이트 스트림이므로 문자 스트림으로 변환해야 함
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			// 스트림 종료
			reader.close();
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 xml데이터
		String response = sb.toString();
		
		// XML File 생성
		File file = new File("C:\\storage", "api3.xml");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}				
		
	}

	public static void m6() {
		
		File file = new File ("C:\\storage", "ap3.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			NodeList items = root.getElementsByTagName("item");
			for(int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);	// Node -> Element 타입으로 다운캐스팅
				/*
				NodeList categories = item.getElementsByTagName("category");
				Node category = categories.item(0);
				-> 이 두줄을 한줄로 바꿀 수 있음
				*/
				Node category = item.getElementsByTagName("category").item(0);  	// 한 줄로 바꾼 결과
				Node obsrValue = item.getElementsByTagName("obsrValue").item(0);
				String strCaregory = null;
				switch(category.getTextContent()) {
				case "PTY": strCaregory = "강수형태"; break;
				case "REH": strCaregory = "습도"; break;
				case "RN1": strCaregory = "강수량(1시간)"; break;
				case "T1H": strCaregory = "기온"; break;
				case "UUU": strCaregory = "동서바람성분"; break;
				case "VEC": strCaregory = "풍향"; break;
				case "VVV": strCaregory = "남북바람성분"; break;
				case "WSD": strCaregory = "풍속"; break;
				}
				System.out.println(strCaregory + " : " + obsrValue.getTextContent());
				
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void m7() {
		
		// 기상청 RSS
		
		String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013061000";
		
		URL url = null;
		HttpURLConnection con = null;

		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		}catch(IOException e) {
			System.out.println("접속 실패");
		}
		
		// 응답 스트림 생성 및 응답 데이터 받기
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		}catch(IOException e) {
			System.out.println("응답 실패");			
		} 
			
		
		//  XML 파일 생성
		
		File file = new File("C:\\storage", "api4.xml");
		
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sb.toString());
			writer.close();
		} catch(IOException e) {
			System.out.println("파일 생성 실패");
		}

	}
	
	public static void m8() {
		
		File file = new File("C:\\storage", "api4.xml");
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			StringBuilder sb = new StringBuilder();
			
			Node title = root.getElementsByTagName("title").item(0);
			sb.append(title.getTextContent()).append("\n");
			
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			sb.append(title.getTextContent()).append("\n");
			
			NodeList dataList = root.getElementsByTagName("data");
			for(int i = 0; i < dataList.getLength(); i++) {
				Element data = (Element)dataList.item(i);
				Node hour = data.getElementsByTagName("hour").item(0);
				Node temp = data.getElementsByTagName("temp").item(0);
				Node wfkor = data.getElementsByTagName("wfkor").item(0);
				sb.append(hour.getTextContent()).append("시 ");
				sb.append(temp.getTextContent()).append("도 ");
				sb.append(wfkor.getTextContent()).append("\n ");
			}
			
			System.out.println(sb.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public static void m9() {
		
		// 기상청 XML
		
		try {
			
			// 접속 
			String apiURL = "https://www.kma.go.kr/XML/weather/sfc_web_map.xml";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 접속 확인
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("API 접속 실패");
				return;
			}
			
			// 바이트 입력 스트림 -> 문자 입력 스트림 -> 버퍼 추가
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			File file = new File("C:\\storage", "sfc_web_map.xml");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			// readLine() 메소드를 이용한 복사
			String line = null;
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");
			}
			
			// 닫기
			bw.close();
			br.close();
			con.disconnect();
			
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.out.println("API 서버 오류");
		}
		
	}
	
	public static void m10() {
		
		File file = new File("C:\\storage", "sfc_web_map.xml");
		
		try {
			
			StringBuilder sb = new StringBuilder();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement(); 	// <current xmlns="current"> 태그
			// System.out.println(root.getNodeName());
			// System.out.println(root.getAttribute("xmlns"));
			Element weather = (Element)root.getElementsByTagName("weather").item(0);	// <weather year="2022">
			
			sb.append(weather.getAttribute("year") + "년 ");
			sb.append(weather.getAttribute("month") + "월 ");
			sb.append(weather.getAttribute("day") + "일 ");
			sb.append(weather.getAttribute("hour") + "시\n");
			
			NodeList locals = root.getElementsByTagName("local");
			
			for(int i = 0; i < locals.getLength(); i++) {
				Element local = (Element)locals.item(i);
				sb.append(local.getTextContent() + " : ");
				sb.append(local.getAttribute("ta") + "℃");
				sb.append(local.getAttribute("desc") + "\n");
				
			}
			
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		m10();
	}

}

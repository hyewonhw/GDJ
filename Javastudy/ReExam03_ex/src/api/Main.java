package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {

	public static void main(String[] args) {
		
		// XML 파싱
		// API응답(XML) -> XML파일 -> Document
		
		// JSON 파싱
		// API응답(JSON) -> JSONObject
		// 중간에 파일생성과정이 필요하지 않음
		
		
		try {
			String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000";
			// 인코딩 필요없기때문에 ServiceKey도 필요없음
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			// 이 두개는 안적어도 지장없음
			
			StringBuilder sb = new StringBuilder();
			// api요청을 읽어들여야하니까 reader필요
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			// 응답됐는지 확인하는 코드 (안해도돼서 안함)
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}// 지금 이 sb에 기상청 그 기상청 rss홈페이지에서 본 xml 전체내용이 들어가있음
			
			// 파싱 결과를 저장할 파일
			File file = new File("weather.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			// StringBuilder에 저장된 응답(XML) 데이터를 JSON으로 변경하기
			JSONObject obj = XML.toJSONObject(sb.toString());
			JSONObject rss = obj.getJSONObject("rss");
			JSONObject channel = rss.getJSONObject("channel");
			
			// JSONObject channel = XML.toJSONObject(sb.toString()).getJSONObject("rss").getJSONObject("channel");
			
			
			
			
			String link = channel.getString("link");
			String description = channel.getString("description");
			String generator = channel.getString("generator");
			String language = channel.getString("language");
			String title = channel.getString("title");
			String pubDate = channel.getString("pubDate");
			bw.write(pubDate + "\n");
			JSONObject item = channel.getJSONObject("item");
			String author = item.getString("author");
			String link2 = item.getString("link");
			String category = item.getString("category");
			bw.write(category + "\n");
			String title2 = item.getString("title");
			JSONObject description2 = item.getJSONObject("description");
			JSONObject body = description2.getJSONObject("body");
			JSONArray dataList = body.getJSONArray("data");
			for(int i = 0; i < dataList.length(); i++) {
				JSONObject data = dataList.getJSONObject(i);
				int sky = data.getInt("sky");
				int temp = data.getInt("temp");
				String wfkor = data.getString("wfKor");
				int wd = data.getInt("wd");
				int hour = data.getInt("hour");
				bw.write(hour + "시, " + temp + "도, " + wfkor + "\n");
			}
			
			bw.close();
			br.close();
			con.disconnect();
			
			/*
			JSONObject d = obj.getJSONObject("a")
					.getJSONObject("b")
					.getJSONObject("c")
					.getJSONObject("d");
			 하나하나 쓰지말고 이렇게 하기
			*/  
			
			// 최종결과
			// pubDate
			// category
			// hour, temp, wfKor
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}	// main

}	// class

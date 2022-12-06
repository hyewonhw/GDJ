package com.gdu.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSearchBook {
	
	public static void main(String[] args) {
		
		String clientId = "tq2feeDwAFErLbNnPmUu";
		String clientSecret = "w1FLigKs5G";
		
		try {
			
			Scanner sc = new Scanner(System.in);;
			System.out.println("검색>>>");
			String book = sc.next();
			URLEncoder.encode(book, "UTF-8");
			
			String apiURL = "https://openapi.naver.com/v1/search/book?query=" + book;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			BufferedReader br = null;
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			con.disconnect();
			
			JSONObject obj = new JSONObject(sb.toString());
//			JSONObject rss = obj.getJSONObject("rss");
//			JSONObject channel = rss.getJSONObject("channel");
			JSONArray items = obj.getJSONArray("items");
			
			File dir = new File("C:/download");
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			File file = new File(dir, sc + ".html");
			PrintWriter out = new PrintWriter(file);
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>검색결과</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<item>");
			
			  for(int i = 0; i < items.length(); i++) {
	               JSONObject element = items.getJSONObject(i);
	               String title = element.getString("title");
	               String link = element.getString("link");
	               String image = element.getString("image");
	               
	               
	               
	               out.println("<a href='" + link + "'><strong>" + title + "</strong></a>");
	               out.println("<br>");
	               out.println("<img src=" + image + ">");
	               out.println("<hr>");
	               
	            }

			
//			for(int i = 0; i < item.length(); i++) {
//				JSONObject element = item.getJSONObject(i);
//				System.out.println("gg");
//			}
			out.println("</item>");
			out.println("</body>");
			out.println("</html>");
			out.close();
			
			System.out.println("성공");
			
			
		} catch (Exception e) {
			
			try {
				
				File dir = new File("C:/download/log");
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				File file = new File(dir, "error_log.txt");
				
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
				String time = sdf.format(date);
				
				PrintWriter out = new PrintWriter(file);
				out.println("예외메시지      " + e.getMessage());
				out.println("예외발생시간    " + time);
				out.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
		}
		
	}
}

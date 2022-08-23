package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ApiMain_x {

	public static void main(String[] args) {
		
		String apiURL = "http://apis.data.go.kr/B552061/AccidentDeath";
		String key = "PBnvMtbLwyfGOMgTVqqScsmhW6PMGPUJbkf8VRdREZGmOdhlp43N64ogyLPUrByDX8elpaLkHr5c1d40oqja0g==";
		File file = new File("C:\\storage\\accident.txt");
		
		try {
			apiURL += "?ServiceKey=" + URLEncoder.encode("PBnvMtbLwyfGOMgTVqqScsmhW6PMGPUJbkf8VRdREZGmOdhlp43N64ogyLPUrByDX8elpaLkHr5c1d40oqja0g==","UTF-8");
			apiURL += "&searchYear=" + URLEncoder.encode("2021", "UTF-8");
			apiURL += "&siDo=" + URLEncoder.encode("1100", "UTF-8");
			apiURL += "&guGun=" + URLEncoder.encode("1125", "UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("10", "UTF-8");
			apiURL += "&pageNo=" + URLEncoder.encode("1", "UTF-8");
			
		} catch(UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "charset=UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));	
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
		String response = sb.toString();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		// 접속 종료
		con.disconnect();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList list = doc.getElementsByTagName("d");
			for(int i = 0; i < list.getLength(); i++) {
				Element acc = (Element)list.item(i);
				
				String occrrncDt = acc.getElementsByTagName("occrrncDt").item(0).getTextContent();
				String occrrncDayCd = acc.getElementsByTagName("occrrncDayCd").item(0).getTextContent();
				String dthDnvCnt = acc.getElementsByTagName("dthDnvCnt").item(0).getTextContent();
				String injpsnCnt = acc.getElementsByTagName("injpsnCnt").item(0).getTextContent();
				 
			}		
						
		} catch(Exception e) {
				e.printStackTrace();
		}

	

	}
}
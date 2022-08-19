package prac3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseMain {

	public static void main(String[] args) {
		
		// 영화 한 편 : Movie 객체에 저장
		// 영화 전체 : List<Movie> 리스트
		
		File file = new File("C:\\storage", "boxoffice.xml");
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			NodeList boxOfficeList = doc.getElementsByTagName("dailyBoxOffice");
			
			for(int i = 0; i < boxOfficeList.getLength(); i++) {
				
				Element boxOffice = (Element)boxOfficeList.item(i);
				
				/*
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();	// 아이템을 써서 0번 인덱스 해주는 이유가 뭐임?
				
				// boxOffice.getElementsByTagName("movieCd") 여기까지가 NodeList
				// boxOffice.getElementsByTagName("movieCd").item(0) 이게 Node
				// boxOffice.getElementsByTagName("movieCd").item(0).getTextContent() 이게 텍스트
				-----> 풀어서 코드를 짜보자면 
				NodeList mocieCdList = boxOffice.getElementsByTagName("movieCd");
				Node movieCdNode = movieCdList.item(0);
				String movieCd = movieCdNode.getTextContent();
				*/
				
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();
				String movieNm = boxOffice.getElementsByTagName("movieNm").item(0).getTextContent();
				String openDt = boxOffice.getElementsByTagName("openDt").item(0).getTextContent();
				String saleAcc = boxOffice.getElementsByTagName("saleAcc").item(0).getTextContent();
				String audiAcc = boxOffice.getElementsByTagName("audiAcc").item(0).getTextContent();
				
			
				Movie movie = Movie.builder()
						.movieCd(movieCd)
						.movieNm(movieNm)
						.openDt(openDt)
						.salesAcc(audiAcc)
						.audiAcc(audiAcc)
						.build();
			
				movies.add(movie);
			}	// for
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Movie movie : movies) {
			System.out.println(movie);
		}
		
	}

}

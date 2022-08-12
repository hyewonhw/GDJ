package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWriter {

	public static void m1() {
		
		// JSON
		// 1. JavaScript Object Notation
		// 2. 자바스크립트 객체 표기법
		// 3. 객체 : { }	-> 하나의 데이터
		// 4. 배열 : [ ]	-> 여러개의 데이터
		
		// JSON-Java(JSON in Java) 라이브러리
		// 1. 객체 : JSONObject 클래스 (Map 기반)
		// 2. 배열 : JSONArray 클래스 (List 기반)
	
		JSONObject obj = new JSONObject();
		obj.put("name", "조혜원");
		obj.put("age", 25);
		obj.put("man", false);
		obj.put("height", 165);
		System.out.println(obj.toString());
	
	}
	
	public static void m2() {
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "제임스");
		obj1.put("age", 30);
		
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "에밀리");
		obj2.put("age", 40);
		
		JSONArray arr = new JSONArray();
		arr.put(obj1);		// 추가도 add아닌 put으로 함 
		arr.put(obj2);
		
		System.out.println(arr.toString());
		
	}
	
	public static void m3() {
		
		// m1() 출력값 복붙한거
		// 공공API에서 받아온 데이터가 이런 형태임
		String str = "{\"name\":\"조혜원\",\"man\":false,\"age\":25,\"height\":165}";
		
		JSONObject obj = new JSONObject(str);
		
		// obj로 반환하는데 string으로 받아서 오류남 캐스팅 필요
		String name = obj.getString("name");
		boolean man = obj.getBoolean("man");
		int age = obj.getInt("age");
		double height = obj.getDouble("height");
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
		
	}
	
	public static void m4() {
		
		String str = "[{\"name\":\"제임스\",\"age\":30},{\"name\":\"에밀리\",\"age\":40}]";
		
		JSONArray arr = new JSONArray(str);
		//System.out.println(arr.toString());		// 정상적으로 인지됐는지 확인하는 용도
		
		// 일반 for문
		for (int i = 0, length = arr.length(); i < length; i++) {	// length()가 메소드라서 여기서 부르면 성능 떨어짐
			JSONObject obj = arr.getJSONObject(i);
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + "," + age);
		}
		
		// 향상 for문 : get() 메소드로 동작. get()메소드는 Object를 반환
		for(Object o : arr) {
			JSONObject 	obj = (JSONObject)o;
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + "," + age);
		}
		
	}
	
	public static void main(String[] args) {
		
		// product.json으로 만들기
		List<String> product1 = Arrays.asList("100", "새우깡", "1500");
		List<String> product2 = Arrays.asList("101", "양파링", "2000");
		List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
		
		List<List<String>> list = Arrays.asList(product1, product2, product3);
		
		// list를 json String으로 만들어서
		// C:\\storage\\product.json 파일에 white()
		
		JSONArray arr = new JSONArray();
		for(List<String> line : list) {
			JSONObject obj = new JSONObject();
			obj.put("number", line.get(0));
			obj.put("name", line.get(1));
			obj.put("price", line.get(2));
			arr.put(obj);
		}
		
		System.out.println(arr.toString());
		// 여기까지가 list를 json으로 바꿔주는 작업
		
		File file = new File("C:\\storage", "product.json");	
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(arr.toString());
		} catch(IOException e) {
			e.printStackTrace();	// 오류를 그대로 화면에 찍어주는애
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

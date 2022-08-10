package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void m1() {
		
		File dir = new File("c:\\storage");
		
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		// new File(경로, 파일명)	경로 = dir로 하겠다 
		File file = new File(dir, "m1.txt");
		
		FileWriter fw = null;
		try {
			// C:\\storage\\m1.txt 파일과 연결되는 문자 출력 스트림 생성 -> 파일생성코드가 없음
			// 출력 스트림이 생성되면 파일도 새로 생성됨
			fw = new FileWriter(file);	// new FileWriter("C:\\storage\\m1.txt")와 같음
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			// finally 블록은 문제가 발생해도 무조건 실행함
			// 스트림 닫을때도 try-catch 필요함
			try {
				if(fw != null) {
					fw.close();					
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "m2.txt");
		FileWriter fw = null;
		try {
			// 출력 스트림 생성(파일도 함께 생성)
			fw = new FileWriter(file);
			
			// 출력할 데이터
			// 1. 한 글자 : int -> 특수문자 중 파일의 끝을 나타내는 EOF가 2byte로 끝나지않아서 int 사용
			// 2. 여러 글자 : char[], String
			int c = 'I';
			char[] cbuf = {' ', 'a', 'm'};
			String str = " IronMan";
			
			// 출력 스트림으로 보내기(출력)
			fw.write(c);
			fw.write(cbuf);
			fw.write(str);		
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void m3() {
		
		// try - catch - resources문
		// 1. resources는 자원을 의미함
		// 2. 여기서 자원은 스트림(stream)을 의미함
		// 3. 스트림의 종료(close)를 자동으로 처리하는 try - catch문을 의미함
		// 4. 형식
		//    try(스트림 생성) {
		//		  코드
		// 	  } catch(Exception e) {
		//        e.printStackTrace();
		//    }
		      
		File file = new File("C:\\storage", "m3.txt");
		try(FileWriter fw = new FileWriter(file)) {
			fw.write("나는 아이언맨이다.");
			fw.write("\n");  // 줄바꿈
			fw.write("너는 타노스냐? \n");
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public static void m4() {
		
		File file = new File("C:\\storage", "m4.txt");
		
		try(FileWriter fw = new FileWriter(file)) {
			
			char[] cbuf = {'a', 'b', 'c', 'd', 'e'};
			String str = "abcde";
			
			fw.write(cbuf, 0, 2); 	// 인덱스 0부터 2글자만 씀 
			fw.write(str, 2, 3); 	// 인덱스 2부터 3글자만 씀
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void m5() {
		
		// FileWriter는 느리기 때문에 
		//빠른 속도가 필요한 경우 BufferedWriter를 추가해서 함께 사용한다.
		File file = new File("C:\\storage", "m5.txt");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			// 출력 메인 스트림
			fw = new FileWriter(file);
			
			// 속도 향상을 위한 보조 스트림
			// 메인 스트림이 없으면 사용 불가
			bw = new BufferedWriter(fw);
			
			bw.write("오늘은 수요일인데 수업이 안 끝나요. ㅎㅎㅎ");
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 메인 스트림은 닫을 필요가 없음
				// 보조스트림만 닫으면 메인은 자동으로 닫힘
				if(bw != null) {
					bw.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		m5();

	}

}

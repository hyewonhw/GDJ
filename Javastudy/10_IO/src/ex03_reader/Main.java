package ex03_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void m1() {
		
		File file = new File("C:\\storage", "m2.txt");
		
		FileReader fr = null;
		try {
			// FileReader 클래스 생성
			// file 객체에 등록된 파일이 없으면 FileNotFoundException발생
			fr = new FileReader(file);
			
			// FileReader 입력 데이터
			// 1. 1글자 : int
			// 2. 여러글자 : char[]
			
			// 1글자를 저장할 변수 
			int c;
			
			// read() 메소드
			// 1. 읽은 문자를 반환
			// 2. 모두 읽어서 읽은 문자가 없으면 -1을 반환 (언제 끝나는지 알 수 있음)
			
			// (번외문제) String str에 파일 내용 저장하기
			// (번1) String str = "";
			// (번2) StringBuilder sb = new StringBuilder();
			/*
			while(true) {
				c = fr.read();
				if(c == -1) {
					break;
				}
				// (번1) str += (char)c;		-> 스트링 반복작업은 성능저하 StringBuilder써줘야함
				// (번2)sb.append((char)c);
				System.out.print((char)c);
			}	
			// (번2)String str = sb.toString();
			// System.out.println(str);
			 */
			StringBuilder sb = new StringBuilder();
			while((c = fr.read()) != -1) {
				sb.append((char)c);
			}
			String str = sb.toString();
			System.out.println(str);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			 try {
				 if(fr != null) {
					 fr.close();
				 }
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		}
		
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "m3.txt");
		// 나는 아이언맨이다.
		// 너는 타노스냐? (수정 전)
		FileReader fr = null;
		
		
		try {
			
			fr = new FileReader(file);
			
			// 5글자를 저장할 배열
			char[] cbuf = new char[5];
			
			// read(char[] cbuf) 메소드
			// 1. 읽은 글자는 cbuf배열에 저장
			// 2. 실제로 읽은 글자 수를 반환 (5글자 읽으면 반환값도 5)
			// 3. 읽은 글자가 없으면 -1 반환
			
			// m3.txt 파일 읽는 과정 (수정 후) cbuf.length 로 확인해보기
			// apple\n
			// mango\n
			// \n
			//		readCnt		cbuf
			// 1		5		a  p  p  l  e
			// 2  		5  	    \n m  a  n  g
			// 3    	2 		o \n  a  n  g
			// 4 		-1 	
			
			while(true) {
				
				int readCnt = fr.read(cbuf);	// cbuf에 나는 아이 들어가고 readCnt에 5 들어감
				
				if(readCnt == -1) {
					break;
				}
				for(int i = 0; i < readCnt; i++) {		// 읽은 글자 수(readCnt)만큼 반복 -> cbuf.length는 남은글자가 3개일때도 5를 반환하기때문에 readCnt
					System.out.print(cbuf[i]);
				}
			} 
			
		} catch (IOException e ) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) {
					fr.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void m3() {
		
		// m3.txt 읽어서 String str에 저장하기
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			/*
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				int readCnt = fr.read();
				if(readCnt == -1) {
					break;
				}
				sb.append(cbuf, 0, readCnt);	// cbuf배열의 인덱스 0부터 readCnt개만 추
			}
			String str = sb.toString();
			System.out.println(str);
			*/
			
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			int readCnt;
			while((readCnt = fr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			String str = sb.toString();
			System.out.println(str);			
			
		} catch (IOException e) {
				e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m4() {
		
		// FileReader는 느리기 때문에
		// BufferedReader를 추가해서 속도를 향상시킨다.
		
		// BufferedReader는 readLine() 메소드를 지원한다.
		// ReadLine() 메소드는 한 줄씩 읽어서 String에 저장한다.
		// 읽은 내용이 없으면 null을 반환한다.
		
		File file = new File("C:\\storage", "m4.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");	
			}
			String str = sb.toString();
			System.out.println(str);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m5() {
		
		// try-catch-resources문으로 m4() 다시 풀기
		// 내려받는 코드 써봐라 하면 쓸 수 있게 외워버리기
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"))) { 
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			String str = sb.toString();
			System.out.println(str);
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
	}
		
	public static void main(String[] args) {
		m5();
	}

}

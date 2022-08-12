package ex04_outputstream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void m1() {
		
		File file = new File("C:\\storage", "b1.bin");
		FileOutputStream fos = null;
		
		try {
			
			// C:\\storage\\b1.bin 파일과 연결되는 바이트 출력 스트림 생성
			fos = new FileOutputStream(file);
			
			// 출력할 데이터 단위 
			// 1. 1개 : int
			// 2. 여러개 : byte[]
			
			// 출력할 데이터 (어떻게 내보낼 수 있느냐)
			int c = 'A';
			String str = "pple mange 맛있다."; 	// 한글 3byte, 나머지 공백까지 1byte 해서 총 22byte
			byte[] b = str.getBytes(StandardCharsets.UTF_8);		// str.getBytes("UTF-8")이라고 해도됨
			
			// 출력
			fos.write(c); 	// int 먼저보냄
			fos.write(b);	// 배열 내보냄
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos != null) fos.close();
				} catch(IOException e) {
					e.printStackTrace();			
			}
		}
	
	}

	public static void m2() {
		
		// 출력 속도 향상을 위한 BufferedOutputStream
		
		File file = new File("C:\\storage", "b2.bin");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			
			fos = new FileOutputStream(file);		// 계단
			bos = new BufferedOutputStream(fos);	// 엘레배이터
			
			String str = "안녕하세요 반갑습니다.";	// 한글 30byte + 공백,마침표 각 1byte = 총 32byte
			byte[] b = str.getBytes("UTF-8");
			
			bos.write(b);
		
		} catch (IOException e) { 
			e.printStackTrace();
		} finally {
			try {
				if(bos != null) bos.close();		// bos만 닫아주면됨 fos를 닫아도 오류는 안나지만 넣을 필요가 없음
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	
	}

	public static void m3() {
		
		// 변수를 그대로 출력하는 DataOutputStream (보조Stream)
		
		File file = new File("c:\\storage", "b3.dat");
		FileOutputStream fos = null;	// 혼자 못씀
		DataOutputStream dos = null;
		
		try {
			
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			
			// 출력할 변수
			String name = "에밀리";
			int age = 30;
			double height = 165.5;	// 실제로 보낼 수 없음 (원래 단위는 int랑 byte배열이기 때문) -> DataOutPutStream의 필요성
			
			// 출력
			dos.writeUTF(name);
			dos.writeInt(age);
			dos.writeDouble(height);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos != null) dos.close();			
			} catch(IOException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	public static void m4() {
		
		// 객체를 그대로 출력하는 ObjectOutputStream
		
		File file = new File("C:\\storage", "b4.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			
			// 1. User를 3개 저장한 ArrayList
			List<User> users = Arrays.asList(
					new User(1, "kim", 30),
					new User(2, "lee", 40),
					new User(3, "choi", 50)
			);
			
			// 2. User 1개
			User user = new User(4, "min", 60);
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
				
			oos.writeObject(users);
			oos.writeObject(user);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null) oos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {	
		m4();	
	}

}

package prac2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void q1() {
		
		// eclipse-jee-2021-03-r-sin-32-x96_64.zip 복사하기
		// 복사할 파일명은 eclipse.zip
		
		// 없으니까 Font.zip 복사할거임
		// 복사된 파일명은 Font1.zip
		
		File src = new File ("C:\\excercise", "Font.zip");
		File cpy = new File ("C:\\excercise", "Font1.zip");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(cpy));
			
					
			byte[] b = new byte[1024];	// 1KB
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { 
				if(bos != null) bos.close();
				if(bis != null) bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void q2() {
		
		// eclipse.zip를 C:\storage 이동
		
		// 없으니까 Font1.zip을 C:\storage로 이동
		// C:\storage로 복사 후 C:\excercise에 있는 파일을 지우기
		
		File src = new File("C:\\excercise", "Font1.zip");
		
		File dir = new File("C::\\storage");
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		File dst = new File("C:\\storage", src.getName());
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dst));
			
			byte[] b = new byte[1024];
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}
			
			bos.close();
			bis.close();
			
			// 원본과 복사본의 크기가 동일하면 원본을 제거
			if(src.length() == dst.length()) {
				src.deleteOnExit();
			}
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {		
		q2();
	}

}

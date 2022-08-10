package ex01_file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Main {
	
	public static void m1() {
		
		// File 클래스
		// 1. 패키지 : java.io
		// 2. 파일 및 디렉터리 관리
		// 3. 생성 방법
		//    1) new File (경로, 파일)   -> 분리해서 등록하는 방법
		//    2) new File (파일)  -> 해당 디렉터리로 이동해있거나 경로 작업을 해줘야함 
		// 4. 윈도우의 경로 구분 방법 : 백슬래시(\)
		// 5. 리눅스의 경로 구분 방법 : 슬래시(/)
		
		
		// 폴더(디렉터리) 만들기
		// File dir = new File("C:", "storage");	-> 분리해서 등록
		File dir = new File("C:\\storage");		// 백슬래시 하나는 \s라는 문자로 인식하기 때문에 2개 입력해줘야함
		
		
		// 존재하지 않으면 만들겠다.
		if(dir.exists() == false) {		// if(!dir.exists())	
			dir.mkdirs();
		}
		
		// 존재하면 삭제하겠다.
		else { 
			// dir.delete();	// 지금 지운다.
			dir.deleteOnExit(); 	// JVM이 종료되면 지운다.
		}
		
	}

	public static void m2() {
		
		// 디렉터리안에 파일생성
		File file = new File("C:\\storage", "my.txt");
		
		try {
			if(file.exists() == false) {
				file.createNewFile();		// Unhandled exception type IOException 발생 if문 try안에 넣어줘야함
			}
			else {
				file.delete();
			}
		} catch(IOException e) {
			// 개발할 때 넣는 catch 블록 코드 
			e.printStackTrace();		// 에러를 콘솔에 찍어라
		}
		
	}
	
	public static void m3() {
		
		File file = new File("C:\\storage", "my.txt");
		
		System.out.println("파일명 : " + file.getName());
		System.out.println("경로 : " + file.getParent());
		System.out.println("전체경로(경로 + 파일명) : " + file.getPath());
		
		System.out.println("디렉터리인가? " + file.isDirectory());
		System.out.println("파일인가? " + file.isFile());	
		
		long lastModifiedDate = file.lastModified();
		// timestamp값으로 반환하기때문에 long타입
		System.out.println("수정한날짜 : " + lastModifiedDate);
		
		String lastModified = new SimpleDateFormat("a hh:mm yyyy-MM-dd").format(lastModifiedDate);
		System.out.println("수정한날짜 : " + lastModified);
		// 수정한날짜 : 오전 09:50 2022-08-10
		
		// file크기 확인하는 방법
		long size = file.length();		// byte단위로 나옴
		System.out.println("파일크기 : " + size + "byte");
		// 영문은 한글자가 1byte
		// 한글은 한글자가 2~3byte
	
	}
	
	public static void m4() {
		
		File dir = new File("C:\\GDJ");
		
		// C:\\GDJ에서 가지고오기
		// 디렉토리, 파일 하나하나 전부 File 객체	-> 배열로 가져오기
		File[] list = dir.listFiles();		// 디렉터리 내부의 모든 파일/디렉터리를 File 객체로 가져옴
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i].getName());
		}
		
	}
	
	public static void m5() {
		
		// 플랫폼마다 다른 경로 구분자 지원
		File file = new File("C:" + File.separator + "storage" + File.separator + "my.txt");	// C:\\storage\\my.txt
		System.out.println(file.getName());
		
	}
	
 	public static void q1() {
		
		// GDJ폴더의 정보 읽어내기
		// 명령프롬포트 dir
		
		File dir = new File("C:\\GDJ");
		File[] list = dir.listFiles();
		int dirCnt = 0;
		int fileCnt = 0;
		long totalSize = 0;
		
		for(File file : list) {
			if(file.isHidden()) {
				continue;	// 숨김파일이면 아무것도안하고 다시 for문 실행
			}
			String lastModified = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(file.lastModified());
			String directory = "";
			String size = "";
			
			if(file.isDirectory()) {
				directory = "<DIR>";
				size = "     ";
				dirCnt++;
			} else if(file.isFile()) {
				directory = "     ";
				size = new DecimalFormat("#,##0").format(file.length()) + "";
				fileCnt++;
				totalSize += Long.parseLong(size.replace(",", ""));
			}
			
			String name = file.getName();
			System.out.println(lastModified + " " + directory + " " + size + " " + name);
			
		}
		System.out.println(dirCnt + "개 디렉터리");
		System.out.println(fileCnt + "개 파일" + new DecimalFormat("#,##0").format(totalSize));
			 
			
		/*	
		File dir = new File("C:\\GDJ");
		long lastModifiedDate = dir.lastModified();
		String lastModified = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(lastModifiedDate);
		
		File[] list = dir.listFiles();
		for(int i = 0; i < list.length; i++) {
			if(dir.isDirectory() == true) {
				System.out.print("<DIR>");
			}
			System.out.println(lastModified + dir.isDirectory() + list[i].getName());
		}
		*/
 	}
		
	public static void q2() {
		
		// C:\storage 디렉터리 삭제하기
		// 디렉터리가 비어 있어야 삭제할 수 있으므로 내부 파일을 먼저 삭제
		String sep = File.separator;
		
		// file 선 삭제 
		File file = new File("C:" + sep + "storage", "my.txt");
		if(file.exists()) {
			file.delete();
		}
		
		// directory 후 삭제
		File dir = new File("C:" + sep + "storage");
		if(dir.exists()) {
			dir.delete();
		}
		
	}
	
	public static void main(String[] args) {
		m1();
	}

}

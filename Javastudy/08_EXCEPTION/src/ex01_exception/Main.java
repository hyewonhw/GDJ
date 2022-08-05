package ex01_exception;

import java.util.Scanner;

public class Main {

	public static void m1() {		// static 붙이는 이유 : m1을 부르는 애가 static이기 때문 / static은 static을 부름
		
		// NullPointException : null값이 어떤 메소드를 호출할 때 발생
		
		String[] hobbies = new String[5];
		
		hobbies[1] = "수영";
		hobbies[2] = "골프";
		hobbies[3] = "영화";
		hobbies[4] = "집콕";
		
		for(int i = 0; i< hobbies.length; i++) {			// hobbies[0]값이 null이기 때문에 나머지도 다 출력이 불가능함
			if(hobbies[i].equals("수영")) {
				System.out.println("취미가 나와 같군요.");
			}
		}
		
	}		

	public static void m2() {		// static 붙이는 이유 : m1을 부르는 애가 static이기 때문 / static은 static을 부름
		
		// NullPointException 회피
		
		String[] hobbies = new String[5];
			
		hobbies[1] = "수영";
		hobbies[2] = "골프";
		hobbies[3] = "영화";
		hobbies[4] = "집콕";
			
		for(int i = 0; i< hobbies.length; i++) {			
			if(hobbies[i] != null && hobbies[i].equals("수영")) {			
				System.out.println("취미가 나와 같군요.");
			}
		}
			
	}	
	
	public static void m3() {
		
		// NumberFormatException : String을 Number타입으로 변경할 때 발생
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>> ");
		String name = sc.nextLine();
		System.out.println("나이 입력 (선택) >>> ");
		String strAge = sc.nextLine();					// 입력없이 Enter만 누르면 strAge는 빈 문자열을 가짐
		int age = Integer.parseInt(strAge);
		
		System.err.println("이름 : " + name + ", 나이 : " + age + "살");
		
	}
	
	public static void m4() {
		
		// NumberFormatException 회피
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>> ");
		String name = sc.nextLine();
		System.out.println("나이 입력 (선택) >>> ");
		String strAge = sc.nextLine();				// 입력없이 Enter만 누르면 strAge는 빈 문자열을 가짐
		
		int age;
		if(strAge.isEmpty()) {
			age = 0;
		} else {
			age = Integer.parseInt(strAge);
		}
		
		System.err.println("이름 : " + name + ", 나이 : " + age + "살");
		
	}
	
	
	public static void main(String[] args) {
		m4();
		
	}
		
	}

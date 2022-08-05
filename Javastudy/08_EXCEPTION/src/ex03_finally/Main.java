package ex03_finally;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// throw
		// 1. 예외 객체를 만들어서 직접 throw할 수 있다.
		// 2. 자바는 예외로 인식하지 않지만 실제로는 예외인 경우에 주로 사용된다.
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("나이입력 >>> ");
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);				
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다.");		// 예외 객체 만들어야함(new)
				// 여기서 만든 new객체는 Exception e 가 받는다. (throw가 던진 것 catch가 받음)
				// 메세지("나이는~입니다.")도 e에 들어있음
			}
			System.out.println(age >= 20 ? "성인" : "미성년자");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			// e에 들어있는 메세지를 호출
		} finally {
			sc.close();		
			System.out.println("finally 블록 실행");
		}

	}

	/*
	// 원리
	class Exception{
		private String message;
		public Exception(String message) {
			this.message = message;
		}
		public String getMessage() {
			return message;	
		}
	}
	class RuntimeException extends Exception{
		public RuntimeException(String message) {
			super(message);
		}
	}
	Exception e = new RuntimeException("이 나이는 잘못되었어요")
	System.out.println(e.getMessage());
	*/
	
	
	
	
}

package ex01_rendom;

public class Ex01 {

	public static void main(String[] args) {
		// 난수 (Random number) 발생
		// Random 클래스, Math 클래스를 주로 활용한다.

//		for (int i = 1; i <= 30; i++) {
//			double c = Math.random();
//			System.out.println(c);
//		}

//		0.0 <- Math.random() < 1.0
//		0% <- Math.random() < 100%

//		확률 처리하기
//		10% 확률로 "대박" , 90% 확률로 "쪽박"

//		for (int i = 1; i <= 1; i++) {
//			if (Math.random() < 0.1) {
//				System.out.println("대박");
//			} else {
//				System.out.println("쪽박");
//			}
//		}

//		2. 난수 값 생성
//		Math.random()        			0.0 <= n < 1.0
//		Math.random() * 5    			0.0 <= n < 5.0
//		(int)(Math.random() * 5)    	  0 <= n < 5
//		(int)(Math.random() * 5) +1       1 <= n < 6

//		for (int i = 0; i < 1; i++) {
//			int dice = (int) ((Math.random() * 6) + 1 );
//			System.out.println("주사위 " + dice);
//		}

//		연습
//		6자리 숫자 인증번호 만들기

//		for (int j = 0; j < 10; j++) {
//			String code = "";
//			for (int i = 0; i < 6; i++) {
//				code += (int) ((Math.random() * 10));
//			}
//			System.out.println(code);
//		}

//		연습
//		아스키코드 65~90 랜덤생성
//      대문자 랜덤생성

//		for (int j = 0; j < 20; j++) {
//			char upper = (char) ((Math.random() * 26) + 65);
//			System.out.printf(upper + " ");
//		}

//		연습
//		대문자 소문자 섞어서 6자리숫자
//		for (int j = 0; j < 6; j++) {
//			char code = ' ';
//			if(code>=65 && )
//
//			code = (char) ((Math.random() * 52) + 65);
//			
//			System.out.print(code);
//		}
		
		for (int i = 0; i < 6; i++) {
			String code = "";
			for (int j = 0; j < 6; j++) {
				if (Math.random() < 0.5) {
					code += (char) ((int) (Math.random() * 26) + 'A');
				} else {
					code += (char) ((int) (Math.random() * 26) + 'a');
				}
			}
			System.out.println(code);
		}

	} // main

}

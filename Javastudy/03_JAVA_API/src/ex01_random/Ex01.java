package ex01_random;

public class Ex01 {

	public static void main(String[] args) {
		
		// 난수(Random number) 발생 
		// Random 클래스, Math 클래스를 주로 활용한다. (실제로 많이 쓰는건 Math)
		
		System.out.println(Math.random());
		
		// 0.0 <= Math.random() < 1.0       범위로 데이터 발생
		// 0%  <= Math.random() < 100%      백분율 해 확률처리할 때 사용가능
		
		// 1. 확률 처리하기
		
		// 10% 확률로 "대박", 나머지 90 % 확률로 "쪽박" 출력하기
		if(Math.random() < 0.1) {
			System.out.println("대박");
		} else {
			System.out.println("쪽박");
		}
		
		
		// 2. 난수 값 생성 
		
		// Math.random()	  				0.0 <= n < 1.0
		// Math.random() * 5                0.0 <= n < 5.0        곱해준숫자만큼 그 범위 안에서 답이나옴
		// (int)(Math.random() * 5)           0 <= n < 5
		
		
		// 연습 01
		// 주사위 2개 던지기
		
		for(int n = 0; n < 2; n++) {
			int dice = (int)(Math.random() * 6) + 1;
			System.out.println("주사위" + dice);
		}
		
		
		// 연습 02
		// 6자리 숫자 인증번호 만들기
		// String code = "123456" => 이런 형태로 출력되게
		String code = "";
		for(int n =0; n < 6; n++) {
			code += (int)(Math.random() * 10);
		}
		System.out.println(code);
		
		// 문자 대/소문자 랜덤하게 출력하기 - 아스키코드표 
		System.out.println((char)((int)(Math.random() * 26) + 65));    // 65 ~ 90 
		System.out.println((char)((int)(Math.random() * 26) + 'a')); // 알아서 계산해줌
		
		System.out.println(1+'A'); // 유니코드로 알아서 계산해줌 출력 값 : 66
		
		
		// 연습
		// 6자리 영문(대문자 + 소문자) 인증번호 만들기
		
		code = "";
		for(int n = 0; n < 6; n++) {
			if(Math.random() < 0.5) {
				code += (char)((int)(Math.random() * 26) + 'A');
			} else {
				code += (char)((int)(Math.random() * 26) + 'a');
			}
		}
		System.out.println(code);
		
		
		// 내가 쓴 답
		// String code = "";
		// for(int n = 0; n < 6; n++) {
		// 		code += (int)(Math.random() * 26);
		// }
		// System.out.println(code);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

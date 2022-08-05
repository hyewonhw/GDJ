package ex04_condition;

public class Ex02 {

	public static void main(String[] args) {
		
		// 논리 연산자
		// 논리 AND : &&, 모두 true이면 true 하나라도 false이면 false 
		// 논리 OR : ||, 하나라도 true이면 true/모두 false이면 false
		// 논리 NOT : !, true는 false로 바꿔주고 false는 true로 바꿔줌
		int a = 10;
		int b = 10;
		
		boolean result1 = (a == 10) && (b == 10); // 각 조건문을 괄호로 묶는 것이 좋음 (권장사항)
		boolean result2 = (a == 10) || (b == 10); 
		boolean result3 = (a == 10) && (b == 20); // false
		boolean result4 = (a == 10) || (b == 20); // true
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		
		int c = 10;
		
		boolean result5 = !(c == 10); // 결과가 true 이기 때문에 바꿔서 false 출력
		boolean result6 = !(c == 20); // 결과가 false 이기 때문에 바꿔서 true 출력
		
		System.out.println(result5);
		System.out.println(result6);
		
		
		// 연습1
		// 변수 n을 10증가시킨 뒤 n이 100보다 크다면 true, 아니면 false
		int n = 95;
		
		boolean result7 = (n += 10) > 100; // 답 : (n+10) > 100 이라고 썼음
		
		System.out.println(n); // 105
		System.out.println(result7); //true
		
		
		
		// 연습2
		// 변수 x를 1 증가시킨 뒤 x가 10과 같으면 true, 아니면 false
		int x = 9;
		
		boolean result8 = ++x ==10;
		
		System.out.println(x);
		System.out.println(result8);	
		
		
		// Short CIrcuit Evaluation
		// 논리 AND : false가 발생하면 더 이상 진행하지 않는다. 최종 결과는 false 이므로
		// 논리 OR : true가 발생하면 더 이상 진행하지 않는다. 최종 결과는  true 이므로
		int i = 10;
		int j = 10;
		
		boolean result9 = (i == 20) && (++j == 11); // 논리 AND

		System.out.println(result9); //false가 발생
		System.out.println(j); // i == 20에서 이미 false가 발생 했기 때문에 ++j는 실행하지 않는다. 
				
				
		boolean result10 = (i == 10) || (++j == 11);// 논리 OR
		
		System.out.println(result10); // true가 발생
		System.out.println(j);  // i == 10 에서 이미 true가 발생 했기 때문에 ++j 실행하지 않음
		
		
		// 연산은 가급적 뒤로 빼는 것이 잘 짠 코드
		// a == 1 && b == 2 && c == 3 && (a+b+c)==6
		
		
	}

}

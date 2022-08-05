package ex03_number;

public class Ex01 {

	public static void main(String[] args) {
		
		// 산술 연산
		int a = 7;
		int b = 2;
		int result1 = a + b;
		int result2 = a - b;
		int result3 = a * b;
		int result4 = a / b; // 몫을 구할 때
		int result5 = a % b; // 나머지값을 구할 때
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		
		// 연습1
		// a = 7 이고, b = 2 이므로
		// a나누기 b는 3.5이다
		double result = (double)a / b; // 하나만 캐스팅해도 자동으로 타입 변환함
		System.out.println(result);
		
		
		// 연습2
		// 25을 2와 5로 나눠보기
		int n = 25;
		int ten = n / 10; // 값이 2가 나오게
		int one = n % 10; // 값이 5가 나오게
		System.out.println(ten);
		System.out.println(one);
		
		// 연습3
		// 90초를 1분 30초로 나눠보기
		int second = 90;
		int m = second / 60; // 출력값이 1이 나오게
		int s = second % 60; // 출력값이 30이 나오게
		System.out.println(m + "분");
		System.out.println(s + "초");
		System.out.println(m + "분" + s + "초");
		
		
	}

}

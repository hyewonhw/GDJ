package ex03_number;

public class Ex01 {

	public static void main(String[] args) {

//		산술 연산
		int a = 7;
		int b = 2;
		int result1 = a + b;
		int result2 = a - b;
		int result3 = a * b;
		int result4 = a / b; // 몫
		int result5 = a % b; // 나머지

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4); // 몫
		System.out.println(result5); // 나머지

//		연습
//	    25을 몫이 2로 나머지 5로 나오게 하기
		int n = 25;
		int ten = n / 10; // 몫 2
		int one = n % 10; // 나머지 5

		System.out.println(ten);
		System.out.println(one);

//		연습
//		90초를 1분 30초로 나눠보기
			
		int second = 90; // 총 초
		int m = second / 60; // 분
		int s = second % 60; // 나머지 초

		System.out.println(m);
		System.out.println(s);

//		연습
//		a = 7이고, b = 2 이므로
//		a 나누기 b는 3.5이다.
//		7.0 나누기 2.0은 3.5이다
		double result = (double) a / b; // 3.5
		System.out.println(result);

	}

}

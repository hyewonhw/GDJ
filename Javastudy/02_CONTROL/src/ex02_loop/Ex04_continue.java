package ex02_loop;

public class Ex04_continue {

	public static void main(String[] args) {

//		continue 문
// 		반복문의 시작 지점으로 이동한다.
//		실행에서 제외할 코드가 있는 경우에 사용한다.

//		1 ~ 100 중에서 3의 배수를 제외하고 모두 더하기
		int sum = 0;
		int i = 0;

//		while (i < 13) { //3의 배수합
//			i++;
//			if (i % 3 == 0) {
//				sum += i;
//				continue;
//			}
//		}
//		System.out.println(sum);
//		
		while (i < 13) { //3의 배수만 빼기
			i++;
			if (i % 3 != 0) {
				sum += i;
			}
		}
		System.out.println(sum);
		
		double a = 123456.123456;
		System.out.printf("%.5f" , a);
		
		
		
	}// main

}

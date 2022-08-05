package ex02_loop;

public class Ex01_for {

	public static void main(String[] args) {
		
		// for문
		// 연속된 숫자를 생성할 때 주로 사용한다.
		// 배열과 함께 자주 사용된다.
		// 형식
		// for(초기문; 조건문; 증감문) {
		// 		실행문
		// }
		// 초기문(한번) -> 조건문(만족하면) -> 실행문 -> 증감문 -> (도돌이표)조건문 -> 실행문 -> 증감문
		
		// 1 ~ 10
		
		for(int n = 1; n <= 10; n++) {
			System.out.print(n);
		}
		// System.out.println(n); -> 여기서 출력하면 11이 출력됨, 실행은 안됨
				
		System.out.println();  // 줄 바꿈
		
		
		// 연습 01
		// 10 ~ 1
		
		for(int n = 10; n >= 1; n--) {
			System.out.print(n);
		}
		
		System.out.println();
		
		
		// 연습 02
		// 구구단 7단 출력
		
		for(int n = 1; n <= 9; n++) {
			System.out.println("7x" + n + "=" + (7 * n));
		}
				
			
		// 연습 03
		// 1 ~ 100 사이의 모든 3의 배수만 출력하기
		// 힌트 : 3으로 나눈 나머지가 0인 숫자
		
		for(int n =1; n <= 100; n++) {
			if(n % 3 == 0) {
				System.out.print(n + " ");
			}
		}
		
		System.out.println();	
		
		
		// 연습 04
		// 1 ~ 100 모든 정수 더하기 (5050 출력)
		
		// 아래코드 참고
		int wallet = 0;
		wallet += 1000;
		wallet += 2000;
		wallet += 3000;
		
		System.out.println(wallet);
		
		int total = 0;
		for(int n = 1; n <= 100; n++) {
			total += n;
		}
		
		System.out.println("전체 합 : " + total);
		
		
		
		// 연습 05
		// begin ~ end 모든 정수 더하기
		// begin과 end중 누가 큰 지 모르는 상황
		// begin을 end보다 항상 작은 값으로 바꾼 뒤 begin ~ end 모두 더하기 진행 -> 출력값 5050
		// begin이 end보다 크다면 begin과 end를 교환

		int begin = 100;
		int end = 1;
		
		
		if(begin > end) {
			int temp;
			temp = begin;
			begin = end;
			end = temp;
		}	
		
		int sum = 0;
		for(int n = begin; n <= end; n++) {
			sum += n;
		}	
		System.out.println(sum);
		
		// 연습 06
		// 평점 (1 ~ 5)에 따른 별(★) 출력하기
		
		int point = 5;
		String star = "";
		for(int n = 0; n < point; n++) {
			star += "★";
		}
		System.out.println(star);
		
	}

}

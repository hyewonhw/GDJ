package ex02_loop;

public class Ex01_for {

	public static void main(String[] args) {

//		for문
//		연속된 숫자를 생성할 때 주로 사용한다.
//		배열과 함께 자주 사용된다.
//		for(초기문; 조건문; 증감문) {
//			실행문
//		}
//		1~10
		for (int n = 1; n <= 10; n++) {
			System.out.println(n);
		}
//		초기문 -> 조건문 -> 실행문 -> 증감문 -> 조건문 -> 실행문 -> 증감문

		System.out.println(); // 줄바꿈

//		연습1
//		10 ~ 1
		System.out.println("연습1");
		for (int n = 10; n >= 1; n--) {
			System.out.println(n);
		}

//		연습2.
//		구구단 7단 출력
		System.out.println("\n연습2 7단 출력");
//		for (int i = 1 ; i  <= 9; i++) {
//			System.out.println();
//			for (int j = 1; j <= 9; j++) {
//				System.out.println(i + "*" + j + "=" + i * j );
//				
//			}
//		}
//		System.out.println();

		for (int j = 1; j <= 7; j++) {
			System.out.println(7 + " x " + j + " = " + 7 * j);
		}
//		연습3
//		1 ~ 100 사이의 모든 3의배수만 출력하기
		System.out.println("\n연습3 3의 배수만 출력하기");
		for (int i = 1; i <= 100; i++) {
			if ((i % 3) == 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println("\n");

//		int wallet = 0;
//		wallet += 1000;
//		wallet += 2000;
//		wallet += 3000;
//		System.out.println(wallet);

//		연습4
//		1 ~ 100 모든 정수 더하기 (5050)
		System.out.println("\n연습4 1~100 더하기");
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		System.out.println("전체 합: " + sum);

//		연습5.
//		begin ~ end 모든 정수 더하기 
//		begin과 end중 누가 큰 지 모르는 상황
//		begin을 end보다 항상 작은 값으로 바꾼 뒤 begin -end 모두 더하기 진행
//		begin이 end보다 크다면 begin과 end를 교환		
		System.out.println("\n연습5 ");

		int begin = 1;
		int end = 100;
		int temp = 0;
		int sum2 = 0;

//		if (begin > end) {
//			temp = begin;
//			begin = end;
//			end = temp;
//			for (int i = begin; i <= end; i++) {
//				sum2 += i;
//			}
//			System.out.println(sum2);
//		} else {
//			for (int i = begin; i <= end; i++) {
//				sum2 += i;
//			}
//			System.out.println(sum2);
//		}
		if (begin > end) {
			temp = begin;
			begin = end;
			end = temp;
		}
		for (int i = begin; i <= end; i++) {
			sum2 += i;
		}
		System.out.println(sum2);

//		연습6
//		평점(1~5)에 따른 별 (★) 출력하기
		System.out.println("\n연습6 ");

		int point = 5;
		String star = "";

//		switch (point) {
//		case 1:
//			for (int i = 1; i <= point; i++) {
//				star = "★";
//			}
//			break;
//		case 2:
//			for (int i = 1; i <= point; i++) {
//				star += "★";
//			}
//			break;
//		case 3:
//			for (int i = 1; i <= point; i++) {
//				star += "★";
//			}
//			break;
//		case 4:
//			for (int i = 1; i <= point; i++) {
//				star += "★";
//			}
//			break;
//		case 5:
//			for (int i = 1; i <= point; i++) {
//				star += "★";
//			}
//			break;
//		default :
//			System.out.println("잘못 입력");
//		}
		System.out.println(star);
		for (int i = 0; i < point; i++) {
			star += "★";
		}
		System.out.println(star);
		
		
		
		//
		//
		
//		double statistics = 0;
//		double finalStatistics=0;
//		int finalPeople = 30;
//		
//		for(int people = 2; people <= finalPeople; people++ ) {
// 		1-(364/365)*(363/365)…*(365-n+1/365
//			statistics +=  ((365-people+1)/(double)365);
//			System.out.println(statistics);
//		}
//		
//		finalStatistics =  1-(365/365 * statistics)/100 ;
//		System.out.println(finalStatistics);
	
			
		
		
			
			
		

	} // mian

}

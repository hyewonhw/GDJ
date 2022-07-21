package ex02_loop;

public class Ex05_nested_for {

	public static void main(String[] args) {

//		1일차 1교시
//		1일차 2교시
//		...
//		1일차 8교시
//		2일차 1교시
//		...
//		3일차 8교시

//		for (int day = 1; day <= 3; day++) {
//			for (int hour = 1; hour <= 8; hour++) {
//				System.out.println(day + "일차 " + hour + "교시 ");
//			}
//		}

//		연습1.
//		구구단
//		System.out.println("\n연습1 99단 출력");
//		for (int i = 2; i <= 9; i++) {
//			System.out.println();
//			for (int j = 1; j <= 9; j++) {
//				System.out.println(i + " x " + j + " = " + i * j);
//
//			}
//		}

//		연습2

//		System.out.println("\n연습2 99단 5x5에서 멈추기");
//		for (int i = 2; i <= 9; i++) {
//			System.out.println();
//			if (i == 6) {
//				break;
//			}
//			for (int j = 1; j <= 9; j++) {
//				System.out.println(i + " x " + j + " = " + i * j);
//				if (i == 5 && j == 5) {
//					break;
//				}
//			}
//		}

		// 라벨 이용한 풀이
//		outer: for (int i = 2; i <= 9; i++) {
//			System.out.println();
//			if (i == 6) {
//				break;
//			}
//			for (int j = 1; j <= 9; j++) {
//				System.out.println(i + " x " + j + " = " + i * j);
//				if (i == 5 && j == 5) {
//					break outer;
//				}
//			}
//		}

//		연습3
//		횡으로 출력

		System.out.println("\n연습1 99단 출력");

		for (int i = 1; i <= 9; i++) {

			System.out.println();
			System.out.printf("%d|", i);
			for (int j = 2; j <= 9; j++) {
				System.out.printf("  " + j + "x" + i + "=" + "%2d", j * i);
			}

		}

//		int a = 1;
//		int b = 2;
//		int abc = a+b;
//		System.out.printf("%2d", a+b);
	}// main

}

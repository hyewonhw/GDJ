package ex01_branch;

public class Ex03_else_if {

	public static void main(String[] args) {
		
		// else if문
		// 조건이 여러 개 사용되는 경우에 각 조건을 처리한다.
		// if(조건) {
		//     실행문
		// } else if(조건) {
		//     실행문 
		// } else if(조건) {
		//     실행문 
		// }
		
		// 나이에 따른 결과 출력
		// 0 ~ 7   : 미취학아동
		// 8 ~ 13  : 초등학생
		// 14 ~ 16 : 중학생
		// 17 ~ 19 : 고등학생
		// 20~     : 성인
		
		int age = 25;
		
		if(age < 0 || age >100) {
			System.out.println("잘못된 나이");
		} else if(age <= 7) {
			System.out.println("미취학아동");
		} else if(age <= 13) {
			System.out.println("초등학생");
		} else if(age <= 16) {
			System.out.println("중학생");
		} else if(age <= 19) {
			System.out.println("고등학생");
		} else {
			System.out.println("성인");
		}
		
		
		// 연습 1
		// 월에 따른 계절 출력
		// 봄  : 3 ~ 5
		//여름 : 6 ~ 8
		//가을 : 9 ~ 11
		//겨울 : 12, 1 ~ 2
		
		int month = 2;
		
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if (month == 12 || month <= 2) {
			System.out.println("겨울");
		} else if (month <=5) { 
			System.out.println("봄");
		} else if (month <= 8) {
			System.out.println("여름");
		} else if (month <= 11) {
			System.out.println("가을");
		}
		
		// 내가 쓴 답
		// if(month < 1 || month > 12) {
		//		System.out.println("잘못된 월");
		// } else if(month >= 3 && month <= 5) {
		//		System.out.println("봄");
		// } else if(month <= 8) {
		// 		System.out.println("여름");
		// } else if (month <= 11) {
		// 		System.out.println("가을");
		// } else {
		// 		System.out.println("겨울");
		// }
		
		
		// 나머지 연산을 활용한 modular 연산
		int mod = month % 12;
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if(mod <= 2) {
			System.out.println("겨울");
		} else if(mod <= 5) {
			System.out.println("봄");
		} else if(mod <= 8) {
			System.out.println("여름");
		} else if(mod <= 11) { 
			System.out.println("가을");
		}
		
		// 연습 2
		// 점수에 따른 학점
		// score      grade
		// 100 ~ 90    A
		// 89 ~ 80     B
		// 79 ~ 70     C
		// 69 ~ 60     D 
		// 59 ~ 0      F
		
		int score = 100;
		char grade;
		
		if(score < 0 || score > 100) {
			grade = 'X';
		} else if(score >= 90) {
			grade = 'A';
		} else if(score >= 80) {
			grade = 'B';
		} else if(score >= 70) {
			grade = 'C';
		} else if(score >= 60) {
			grade = 'D';
		} else {
			grade = 'F';
		}
		
		System.out.println("점수는 " + score + "점");
		System.out.println("학점은 " + grade + "학점");
			
		// 내가 쓴 답
		// if(score < 0 || score > 100) {
		//		System.out.println("잘못된 점수");
		// } else if(score >= 90) {
		//		System.out.println("A"); 
		// } else if(score >= 80) {
		// 		System.out.println("B"); 
		// } else if(score >= 70) {
		// 		System.out.println("C");
		// } else if(score >= 60) {
		//  	System.out.println("D");
		// } else {
		//  	System.out.println("X"); 
		// }
		
		
		// 연습 3
		// 오직 일 수만 고려
		// 1일이 수요일이다. 
		// n일 후 무슨 요일인지 출력하기.
		// 7로 나눈 나머지값을 계산해보면 수1 목2 금3 토4 일5 월6 화0
		int day = 1;
		int n = 1;
		String weekName; 
		
		day += n;
		
		if(day % 7 == 0) { 
			weekName = "화";
		} else if(day % 7 == 1) {
			weekName = "수";
		} else if(day % 7 == 2) {
			weekName = "목";
		} else if(day % 7 == 3) {
			weekName = "금";
		} else if(day % 7 == 4) {
			weekName = "토";
		} else if(day % 7 == 5) {
			weekName = "일";
		} else {
			weekName = "월";
		}
		
		System.out.println(weekName + "요일");
		
		
		//내가 쓴 답
		//int i = day % 12;
		//if (i >7 || i < 0) {
		//		System.out.println("잘못된 요일");
		// }else if(i == 1) {
		//		System.out.println("수요일");
		// } else if (i == 2) {
		//	 	System.out.println("목요일");
		// } else if (i == 3) {
		//		System.out.println("금요일");
		// } else if (i == 4) {
		//		System.out.println("토요일");
		// } else if (i == 5) {
		//		System.out.println("일요일");
		// }else if (i == 6) {
		//		System.out.println("월요일");
		// }else if (i == 0) {
		//		System.out.println("화요일");
		// }
		
	}

}

package Prac_0729;

import java.util.Scanner;

public class ex01 {

	public static void main(String[] args) {
		
		// 성공
		// 1. 점수와 학년을 입력받아 60점 이상이면 합격, 60점 미만이면 불합격을 출력
		// 4학년인 경우 70점 이상이어야 합격이다.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("점수를 입력하세요 >>>");
		int score = sc.nextInt();
				
		System.out.println(score >= 60 ? "학년을 입력하세요 >>>" : "불합격!");
		int grade = sc.nextInt();
		
		if(grade != 4 && score >= 60) {
			System.out.println("합격!");
		}else {
			System.out.println(grade == 4 && score >=70 ? "합격" : "불합격");
		}
		
		
		
		// 4학년만 예외 1~3,5,6학년은 점수기준같아야함
		// if(grade == 4 && score >=70) {
		// 		System.out.println("합격!");
		// } else {
		// 		System.out.println("불합격!");
		// }



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

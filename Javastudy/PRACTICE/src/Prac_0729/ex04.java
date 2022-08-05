package Prac_0729;

import java.util.Scanner;

public class ex04 {

	public static void main(String[] args) {
		
		// 문제점-랜덤생성 5개하겠다고 입력해도 무수히 많이 생성해버림
		//        10개씩 생성하고 줄바꿔줘야하는데 어떻게하더라
		
		Scanner sc = new Scanner(System.in);
		System.out.println("몇 개의 랜덤을 생성할까요? >>>");
		int num = sc.nextInt();
		
		if(num < 1 || num > 100) {
			System.out.println("다음에는 1~100사이로 입력하세요!"); // 여기까진 정상
		} else if(num >= 1 || num <= 100) {
			for(int n = 1; n < 100; n++) {
				System.out.print((int)(Math.random() * 100) + "\t");
			}
		}
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}

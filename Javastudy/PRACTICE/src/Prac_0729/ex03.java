package Prac_0729;

import java.util.Scanner;

public class ex03 {

	public static void main(String[] args) {
		
		int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int[] count = new int[unit.length];
		
		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하시오 >>>");
		int money = sc.nextInt();
		
		for(int i = 0; i < unit.length; i++) {
			count[i] = money / unit[i];
			money = money % unit[i];
			System.out.println(unit[i] + "원" + count[i] + "개");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

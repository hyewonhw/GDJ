package quiz02_updown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {
	
	private int rand; 
	private int count;
	private Scanner sc;
	
	
	public UpDownGame() {
		rand = (int)(Math.random() * 100 + 1);
		sc = new Scanner(System.in);
	}
	
	public int input() {
		count++;
		System.out.println("입력 >>> ");
		int n = sc.nextInt();
		if(n < 1 || n > 100) {
			throw new RuntimeException("1 ~ 100 사이 정수만 입력할 수 있습니다.");
		}
		return n;
	}
	
	public void play() {
		while(true) {
			try {
				int n = input();
				if(n < rand) {
					System.out.println("Up!");
				} else if (n > rand) {
					System.out.println("Down!");
				} else { 
					System.out.println(rand + " 정답입니다." + count + "번만에 정답");
					break;
				}
			} catch(InputMismatchException e ) { 
				sc.next();
				System.out.println("정수만 입력 가능합니다.");
				
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		
		// 대입 연산
		int score = 100; // 등호(=)가 대입 연산자이다.
		
		//score <- 100; // 100을 score에 보내주겠다.
		System.out.println(score);
		
		// 연습1
		// x에 10이 있고, y에 20이 있다.
		// x와 y에 저장된 값을 서로 교환하시오.
		int x = 10;
		int y = 20;
		int temp;
		
		temp = x;
		x = y;
		y = temp;
		
		System.out.println(x);
		System.out.println(y);
		
		// 복합 대입 연산자
		// +=, -=, *=, /=, %= 등
		int wallet = 0;
		wallet = wallet + 5000;
		wallet += 5000; // wallet = wallet + 5000
		wallet -= 3000; // wallet = wallet - 3000
		System.out.println(wallet);
		
		// 연습2
		// 통장잔액(balance)에서 이자 5%를 받았음을 나타내자
		long balance = 10000;
		balance *= 1.05;
		// long    double      balance를 double로 promotion해서 처리함
		System.out.println(balance);
		
		// balance = balance * 1.05; -> 실패. 결과가 double이기 때문에 long balance에 저장할 수 없다.
		// balance = (balance * 1.05;) -> 가능하게 하려면 먼저 계산하라고 묶고
		// balance = (long)(balance * 1.05); -> 성공. balance * 1.05결과를 long으로 강제 casting 해서 저장할 수 있다.
		
		// balance = balance + balance * 0.05; -> 실패. balance + balance * 0.05 결과가 double이기 때문에 저장할 수 없다.
		// balance = balance + (long)(balance * 0.05); -> 성공. balance + balance * 0.05 결과를 long으로 casting해서 저장할 수 있다.
		
		
	}

}

package quiz01_updown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {

	// 필드
	private int rand = (int)(Math.random() * 100 + 1);	// 1 ~ 100 사이 난수
	private int count;	// 시도 횟수
	private Scanner sc; 
	
	// 생성자
	public UpDownGame() {
		// rand, sc 만들기
		rand = (int)(Math.random() * 100 + 1);
		sc = new Scanner(System.in);
	}

	// 입력
	public int input() {		// 예외발생 
		try {
			count++;
			System.out.print("입력 >>> ");
			int n = sc.nextInt();
			if(n < 1 || n > 100) {
				throw new RuntimeException("1 ~ 100 사이 정수만 입력할 수 있습니다.");
			}
			return n;		// 이게 진짜 return
		} catch(InputMismatchException e) {
			System.out.println("정수만 입력할 수 있습니다.");
			sc.next();	// 잘못 입력된 문자열 먹어 치우기
			input();	// input부름 -> 다시돌아가라 재귀호출(자기가 자기를 부름)
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
			input();
		}
		return 0; 		// 얘는 메소드 이름 input()에 빨간줄 뜨는거 막아주는 용도 실 사용 X
	}
	
	// 실행
	public void play() {

		// 맞힐때까지 무한루프(while)
		while(true) {
			int n = input();
            if (n < rand) {
            	System.out.println("UP!");
            } else if(n > rand) {
            	System.out.println("DOWN!");
            } else { 
            	System.out.println(count + "번만에 정답!");
            	break;
            }
        }	
	}
	
}

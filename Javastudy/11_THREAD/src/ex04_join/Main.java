package ex04_join;

public class Main {

	public static void main(String[] args) {
		
		// Calculator를 2개 준비
		// 작업을 반으로 나눠서 진행

		/*
		Calculator calc1 = new Calculator();		
		int total1 = calc1.add(1, 5000);
		
		Calculator calc2 = new Calculator();
		int total2 = calc2.add(5001, 10000);
		
		System.out.println(total1 + total2);	
		---> 그냥 메인일때 계산기는 두개지만 계산하는사람은 하나임
		 	사람을 두명 써야 계산기 하나가 일할때 하나는 놀고있는 상황이 발생하지 않음
		 	계산기를 Thread로 처리해 동시에 연산
		*/
		
		// Calculator가 동시에 연산을 수행하려면 Calculator를 Thread로 처리해야 함
		Calculator calc1 = new Calculator(1, 50000);
		Thread thread1 = new Thread (calc1);
		thread1.start();	// 1번째 계산기 동작 시작
		
		Calculator calc2 = new Calculator(50001, 100000);
		Thread thread2 = new Thread (calc2);
		thread2.start();	// 2번째 계산기 동작 시작
		
		// 모든 계산기의 동작이 끝날때까지 기다린다.
		// join() : 스레드가 종료(die)될때까지 기다림.
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(thread1.isAlive());	// 살아있는지 확인
		System.out.println(thread2.isAlive());
		
		System.out.println(calc1.getTotal() + calc2.getTotal());	// 출력값 : 0
		// 단독으로 동작하는 계산기 두개가 있음
		// 스레드가 계산하는 시간을 기다리지않고 메인에서 getTotal 출력해버림
		// 계산이 끝나지 않았을때 total은 0이기 때문에 출력값이 0이 나옴
		
	}

}

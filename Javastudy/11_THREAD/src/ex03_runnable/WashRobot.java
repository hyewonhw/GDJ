package ex03_runnable;

// 스레드 생성 방법
// 1. Runnable 인터페이스 구현
// 2. public void run() 오버라이드

public class WashRobot extends Robot implements Runnable {
	// 여기서 extends로 Robot을 걸었기 때문에 Thread를 다른방법으로 생성해야함
	
	private String name;
	
	public WashRobot(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println(name + " 빨래중");
		
	}
	
}

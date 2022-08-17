package ex01_thread;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("main 시작");
		
		Process process1 = new Process("연산");
		process1.start(); 	// Process 클래스의 오버라이드된 run() 메소드가 호출
		
		Process process2 = new Process("제어");
		process2.start();
		
		System.out.println("main 종료");
		// main이 원래 혼자서(한명이) 시작, start, 종료를 순차적으로 실행
		// 스레드 메소드를 사용하면 한명을 더 사용하는 개념
		// process에 thread를 extends했으니 main은 start에 실행하라고 문만 두드리고 감
		// main은 시작,종료를 출력하는일만 함

	}

}

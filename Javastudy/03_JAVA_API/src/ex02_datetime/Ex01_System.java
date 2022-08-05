package ex02_datetime;

public class Ex01_System {

	public static void main(String[] args) {
		
		// system class
		
		// 1. 타임 스탬스 (timestamp)
		// 1970-01-01 0:00부터 1/1000초마다 증가하는 long 타입의 정수값 (1초마다 천씩늘고있음)
		// 파일이름, 경과시간 계산할 때 쓰임
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		
		
		// 2. 나노타임(nanoTime)
		// s > ㎳(밀리초 : 1/1000) > ㎲ (마이크로초 : 1/100만) > ㎱ (나노초 : 1/ 10억)
		// 나노초 (㎱) 값을 가지는 long 타입의 정수값
		// 어떤 기준일자는 없어서 두 개의 나노초(㎱) 사이의 경과시간 계산용
		long beginTime = System.nanoTime();
		int total = 1 + 2 + 3 + 4 + 5;
		long endTime = System.nanoTime();
		System.out.println(total + "계산에 걸린 시간 : " + (endTime - beginTime) + "㎱");
		
		
		           					
		
		
		
		
		
		
		
		
		
		
	} 

}

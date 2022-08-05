package ex07_constructor;

public class Computer {

	// 생성자
	// 1. 객체 생성할 때 사용되는 특별한 메소드
	// 2. 특징
	//    1) 메소드 이름이 클래스 이름과 같다.
	//	  2) 반환타입이 존재하지 않는다.
	// 3. 생성자는 칠드 초기화 용도로 사용
	
	// 디폴트 생성자(Default Constructor)
	// 1. 개발자가 생성자를 만들지 않으면 자바가 자동으로 생성하는 생성자 / <-> 개발자가 만들면 사용되지 않음
	// 2. 아무 일도 안하는 형태  
		
	// 필드값
	String model;
	int price;
	
	// 생성자
	Computer() {
		// 오버로딩해서 짝찾아줌 성공
	}
	
	Computer(String pModel, int pPrice){    
		model = pModel;
		price = pPrice;
	}
	
	// 메소드
	void printComputerStatus() {
		System.out.println("모델 : " + model);
		System.out.println("가격 : " + price);
	}	
	
}

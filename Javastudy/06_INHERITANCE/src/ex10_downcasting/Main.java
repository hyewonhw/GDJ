package ex10_downcasting;

public class Main {

	public static void main(String[] args) {

		// 클래스타입 : Person
		// 객체(인스턴스) : p
		Person p = new Alba();	// 업캐스팅으로 알바생
		
		
		// instanceof 연산자 
		// 특정 인스턴스가 어떤 클래스타입인지 점검하는 연산자
		// 해당 클래스타입이면 true 반환, 아니면 false 반환
		
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);
		
		
		// p가 Student타입의 인스턴스이면 study() 메소드를 호출할 수 있다.
		if(p instanceof Student) {
			((Student) p).study();		// 다운캐스팅 자동완성해줌 / p를 Student타입으로 캐스팅
		}	// p. + ctrl + space
		
		if(p instanceof Alba) {
			((Alba) p).work(); 	// p를 Alba로 바꾸고 work를 호출해라
		} // Person p = new Student();로 바꾸면 호출 불가
		
		
		
		
		
		
		
		
		
	}

}

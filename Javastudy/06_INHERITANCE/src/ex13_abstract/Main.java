package ex13_abstract;

public class Main {

	public static void main(String[] args) {
		
		// Shape 클래스타입의 객체는 존재할 수 없는 객체이다.
		// abstract 처리해서 객체의 생성을 막을 수 있다. -> 추상클래스 처리했기 때문에 객체가 생성안됨
		/*
		Shape s1 = new Shape("도형");		-> 추상 클래스 객체는 못 만듬
		System.out.println(s1.getType());
		System.out.println(s1.getArea());
		*/
		
		Shape s2 = new Circle("원", 1);
		System.out.println(s2.getType());
		System.out.println(s2.getArea());
	}

}

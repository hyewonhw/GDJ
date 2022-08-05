package ex16_interface;

// extends Pet : 애완동물이다.
// implements Walkable : 산책이 된다.

public class Dog extends Pet implements Walkable {
	// 이름만 줄거기때문에 필드값 없이 생성자만 
	public Dog(String petName) {
		super(petName);
	}
	
	

}

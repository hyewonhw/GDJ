package ex08_override;

public class Square extends Rectangle {
	
	// 이미 부모인 Rectangle이 가지고 있는 필드값으로 충분
	// 필드가 없으므로 사용자 자동완성못함
	// 그럴 때 사용하는게 Constructors from superclass

	public Square(String type, double width) {
		super(type, width, width);
		// 너비만 받아와서 그 너비를 슈퍼클래스의 너비와 높이에 사용하겠다
	}
	// 슈퍼클래스 그대로 사용하면 되기때문에 오버라이드 필요없음
	
	
	
}

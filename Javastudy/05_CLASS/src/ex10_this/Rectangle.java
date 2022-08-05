package ex10_this;

public class Rectangle {

	// 필드
	private int width;
	private int height;
	
	// 생성자
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
		
	}
	
	public Rectangle(int n) {
		this(n, n); // 여기서 this는 위 메소드의 Rectangle을 말함
					// 인수가 2개인 다른 생성자를 호출한다. 
					// (n, n)은 int width 에 n을, int height에 n 을 넣겠다는 뜻
		
		// this.width = n;
		// this.height = n;
	}

	// 메소드
	public int getArea() {
		return width * height;
	}

	public int getCircumference() {
		return 2 * (width + height);
	}

	
	
	
	
	
	
}

package ex08_override;

public class Shape {

	private String type;	// 도형의 종류

	public Shape(String type) {
		super();
		this.type = type;
	}
	public double getArea() {	// 실행용도가 아닌 호출용도 실행은 circle에서 override한 getArea가 실행
		return 0;
	}
	public void info() {
		System.out.println("도형의 종류 : " + type);
	}
}

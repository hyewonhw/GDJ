package ex08_override;

public class Circle extends Shape {

	private double radius;

	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2); 	// radius의 2승
	}
	
	@Override
	public void info() {
		super.info(); 		// super = Shape이기때문에 살려두기
		System.out.println("반지름 : " + radius);
		System.out.println("넓이 : " + getArea());
	}
	
}

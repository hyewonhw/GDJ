package ex14_interface;

public class Circle implements Shape{
	
	private double radius;
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return PI * Math.pow(radius, 2);	// Math.PI값 대신 Shape에서 선언해둔 PI값  
	}
	
	

}

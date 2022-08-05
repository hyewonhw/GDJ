package quiz01;

public class Americano extends Espresso{

	private String type;

	public Americano(String origin, int water, String type) {
		super(origin, water);
		this.type = type;
	}
	
	@Override
	public void info() {
		super.info();		//원두정보 물의양 슈퍼클래스가 가지고있으므로 필요한건 타입
		System.out.println(type + " 아메리카노");
	}
	
	
	
}

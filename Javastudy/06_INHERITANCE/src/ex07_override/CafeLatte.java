package ex07_override;

public class CafeLatte extends Espresso {

	private int milk;	// 사용은 안함
	
	@Override
	public void taste() {
		System.out.println("부드럽다");
	}
	
}

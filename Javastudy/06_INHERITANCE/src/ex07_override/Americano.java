package ex07_override;

public class Americano extends Espresso {

	private int extraWater;		// 사용은 안함
	
	// ctrl + space 눌러서 자동완성
	@Override
	public void taste() {
		//super.taste();	// 여기서 super는 espresso이기 때문에 필요없는경우에는 지워줌
		System.out.println("덜 쓰다");
	}

	/*
	
	@Override
	public void taste() {		
		System.out.println("덜 쓰다");
	}
	
	*/
	
}

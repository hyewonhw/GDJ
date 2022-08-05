package ex07_override;

public class Espresso extends Coffee {

	@Override
	public void taste() {		
		System.out.println("쓰다");
	}
	
	/*
	// 이렇게만 써도 똑같은 모습으로 썼기 때문에 오버라이드를 작성하지않아도 자동으로 오버라이드됨
	// taste를 작성할때 오타가 나도 오류가 안뜨기 때문에 오버라이드를 작성해주는것이 좋음
	public void taste() {		
		System.out.println("쓰다");
	}
	*/
	
}

package ex04_method;

public class VendingMachine {
	// 이 첫줄을 해석할 줄 알아야함 
	String getCoffee(int money, int button) {
		
		String[] menu = {"아메리카노","카페라떼"};
	
		return menu[button - 1] + " " + (money / 1000) + "잔";
						
		/*
		String[] menu = {"","아메리카노","카페라떼"};
		
		return menu[button] + " " + (money / 1000) + "잔";
		*/
		// 아예 0번을 공백으로 비워두고 1번은 아메리카노 2번은 카페라떼로 만들어버려도 같은 출력
	}	
	

}

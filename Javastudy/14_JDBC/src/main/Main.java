package main;

import controller.ContactController;

public class Main {

	public static void main(String[] args) {
		
		// Controller -> Service -> DAO 순서로 실행
		
		new ContactController().play(); // 생성자 호출.메소드 호출
		

	}

}

package ex03_singleton;

public class Main {

	public static void main(String[] args) {
		
		// singleton 객체는 하나만 생성된다.
		
		User user1 = User.getInstance();
		System.out.println(user1);
		
		User user2 = User.getInstance();
		System.out.println(user2);
		
		// 출력찍어보면 하나의 유저가 user1, user2 인것을 알수있음
		// 여러개의 객체가 만들어지면 안되는경우에 사용
		
		
		// User user = new User();  	외부에서는 새로운 객체를 생성할 수 없음
		
	}

}

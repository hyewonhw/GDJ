package ex04_builder;

public class Main {

	public static void main(String[] args) {
		
		User user = User.builder()
				.userNo(1)
				.id("admin")
				.email("admin@naver.com")
				.builder();		// 종료
		
		System.out.println(user);

	}

}

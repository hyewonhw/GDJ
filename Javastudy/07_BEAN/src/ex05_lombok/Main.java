package ex05_lombok;

public class Main {

	public static void main(String[] args) {
		
		// Setter
		User user1 = new User();
		user1.setUserNo(1);
		user1.setId("admin");
		user1.setEmail("admin@naver.com");
		System.out.println(user1);
		
		// Constructor
		User user2 = new User(1, "admin", "admin@naver.com");
		System.out.println(user2);
		
		// Builder
		User user3 = User.builder()
				.userNo(1)
				.id("admin")
				.email("admin@naver.com")
				.build();				
		System.out.println(user3);
		
		
		
		
		
		
		
		
		
		
	}

}

package ex08_access_modifier;

public class UserMain {

	public static void main(String[] args) {
		
		User user = new User();
		
		user.setId("admin");  
		// admin이 매개변수 pId로 가서 id = pId를 통해 필드값 id에 접근
		System.out.println(user.getId());
		
		user.setPassword("1234");
		System.out.println(user.getPassword());
		
		user.setEmail("admin@web.com");
		System.out.println(user.getEmail());
		
		user.setPoint(1000);
		System.out.println(user.getPoint());
		
		System.out.println(user.getVip());
		
		
	}

}

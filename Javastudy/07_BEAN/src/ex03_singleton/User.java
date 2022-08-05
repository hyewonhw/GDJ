package ex03_singleton;

public class User {
	
	// static 필드
	// 객체 생성 이전에 메모리에 미리 만들어 두는 필드
	private static User user = new User(); 	// 디폴트 타입 /static 처리 왜 하는지 찾아보기
	
	private User() {		// private이기 때문에 User 객체 생성은 User 내부에서만 가능
		
	}
	
	// static 메소드 (클래스 메소드)
	// 클래스가 생성될 때 함께 생성되는 메소드
	// 객체 생성 이전에 만들어지기 때문에 객체로 접근하지 않는다.
	// 클래스 이름으로 접근한다.
	// User user = User.getInstance() 라고 호출하는방법밖에없다. / 캘린더에서 이렇게 썼음 열어서 확인해보기
	public static User getInstance() {
		return user;
	}

}

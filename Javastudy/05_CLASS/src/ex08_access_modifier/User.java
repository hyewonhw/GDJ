package ex08_access_modifier;

public class User {

	// 필드는 private이다.
	private String id;
	private String password;
	private String email;
	private int point;
	private boolean isVip;
	
	// 메소드는 public이다.
	public String getId() {
		return id;               // 아이디를 반환해주겠다.
	}
	
	public void setId(String pId) {
		id = pId;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String pEmail) {
		email = pEmail;
	}
	
	
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int pPoint) {
		point = pPoint;
		setVip(point >= 10000);
	}
	
	
	
	public boolean getVip() {
		return isVip;
	}
	
	
	// 포인트가 1000인데 vip를 public으로 하면 true/false를 조작할 수 있음.
	// private로 바꾸고 setPoint에서 점수로 true/false를 출력할 수 있게 함
	private void setVip(boolean pVip) {
		isVip = pVip;
	}
	
	
}

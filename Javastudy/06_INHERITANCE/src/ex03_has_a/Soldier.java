package ex03_has_a;

public class Soldier  {

	// 필드
	private Gun gun; // 총을 가지고있다

	// 메소드
	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}
	
	// 캡슐화
	public void reload(int bullet) {
		gun.reload(bullet);
	}
	// 군인이 총알을 받으면 총에 넣는다
	
	public void shoot() {
		gun.shoot();
	}
	// 군인이 총을 쏘겠다
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

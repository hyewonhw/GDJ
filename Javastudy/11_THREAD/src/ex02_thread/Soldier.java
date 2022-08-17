package ex02_thread;

public class Soldier extends Thread {

	private String name;	// soldier name
	private Gun gun;
	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void shoot() {
		System.out.print("[" + name + "] : ");
		gun.shoot();
	}
	
	@Override
	public void run() {
	
		try {
			// 1초에 한 발씩 쏘기
			while(gun.getBullet() != 0) {	// 총알이 0이 아니면
				shoot();	// shoot() 메소드를 호출 
				Thread.sleep(1000);	
			}
		} catch(InterruptedException e) {	// Interrupted -> 가로채기
			e.printStackTrace();
		}
	}
	
	
}

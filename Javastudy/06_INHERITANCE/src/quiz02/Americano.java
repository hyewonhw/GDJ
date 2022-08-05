package quiz02;

public class Americano{		// 자식클래스 아님 오버라이드 쓸 수 없는거 확인

	private Espresso espresso;
	private int shot;
	private String type;
	
	public Americano(Espresso espresso, int shot, String type) {
		super();
		this.espresso = espresso;
		this.shot = shot;
		this.type = type;
	}

	public void info() {
		espresso.info();
		System.out.println(shot + "샷");
		System.out.println(type + " 아메리카노");
	}
	
}

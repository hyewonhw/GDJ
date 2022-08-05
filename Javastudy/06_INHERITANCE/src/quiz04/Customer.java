package quiz04;

public class Customer {

	private int money;
	private int bonusPoint;
	private int total;
	private Product[] cart = new Product[10];
	private int idx;	// cart에 담긴 Product의 개수, cart 배열의 인덱스
	
	// 디폴트 생성자이기 때문에 생략가능
	// new Customer() 가능
	
	// 메소드
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBonusPoint() {
		return bonusPoint;
	}
	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	// 카트나 인덱스 바꿔치기할 수 없게 머니와 보너스포인트만 메소드생성
	
	
	// buy() 메소드
	public void buy(Product product) { // Product product가 업캐스팅
		int price = product.getPrice();
		// 가진 돈 보다 비싼 물건은 못 산다.
		if(money < (price)) {
			System.out.println(product.getName() + " 사려면 돈이 " + (price - money) + "원 부족합니다.");
			return;
		}
		// 카트가 가득 차면 물건을 못 산다. (10개가 최대)
		if(idx == cart.length) {
			System.out.println("카트가 가득 찼습니다.");
			return;
		}
		// 구매
		cart[idx++] = product;		// 카트 0번에 넣어주고 인덱스는 1로 늘어남 후위연산이기 때문에
		money -= price;
		total += price;
		bonusPoint += price * 0.1; 	// 10% 이기 때문에 0.1 곱해주기
	}

	
	
	// receipt() 메소드
	public void receipt() {
		
		System.out.println();
		System.out.println("=====영수증=====");
		
		// 물건을 안 샀다.
		if(idx == 0){
			System.out.println("구매한 물건이 없습니다.");
			return;
		}
		// 구매 총액 구하기 및 출력
		
		for(int i = 0; i < idx; i++) {
			Product product = cart[i];
			System.out.println(product.getName()+ "  " + product.getPrice() + "원");
	
		}
		System.out.println("--------------------------------");
		System.out.println("구매총액" + total + "원");
		System.out.println("보너스" + bonusPoint + "원");
		System.out.println("남은돈" + money + "원");
	
	}
}

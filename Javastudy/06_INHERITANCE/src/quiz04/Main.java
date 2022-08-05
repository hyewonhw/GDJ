package quiz04;

public class Main {

	public static void main(String[] args) {
		
		Customer customer = new Customer();				// 아직 돈이 없는 고객
		
		customer.setMoney(10000);						// 10000원이 생긴고객
		customer.buy(new Snack("홈런볼", 1500));		// 1500원짜리 홈런볼을 산다.(카트에 담는다.)
		customer.buy(new Meat("한우", 5000));			// 5000원짜리 한우를 산다.(카트에 담는다.)
		customer.buy(new Milk("서울우유" , 2500));		// 2500원짜리 서울우유를 산다.(카트에 담는다.)
		customer.buy(new Meat("불고기" , 5000));		// 구매 불가
		customer.receipt();								// 영수증을 본다.
		/*
		 홈런볼		1500원
		 한우 	5000원
		 서울우유 	2500원
		 ------------------
		 구매총액 	9000원
		 보너스		900원
		 남은돈 	1000원	 
		 */
		
		
		
		

	}

}

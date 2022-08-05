package Prac_0729;

public class Bank {

	// 문제 9 다른방식의 풀이
	
	
	// 필드 
	private String accNo;
	private long balance;
	
	// 생성자
	public Bank(String accNo, long balance) {	// Bank b = new Bank("1234",50000)
		this.accNo = accNo;
		this.balance = balance;
	}
	
	// 메소드
	// 1. 입금(마이너스 입금 불가)
	public void deposit(long money) { 	// b.deposit(50000) 오만원입금하겠다는 뜻
		if(money < 0) {
			return;						// 반환타입이 void인 메소드를 종료하는 코드 -> void 일때 return 으로 끝낼 수 있는거임(한쌍)
		}
		balance += money;
	}
	
	// 2. 출금(마이너스 출금 불가 + 잔액보다 큰 금액 출금 불가)
	// 실제로 출금된 금액을 반환
	public long withdrawal(long money) { 	// long 출금액 = b.withdrawal(50000); -> 5만원 출금 불가능할땐 0이 반환됨
		if(money <= 0 || money > balance) {
			return 0;
		}
		balance -= money;
		return money;
	}
	
	// 3. 이체(출금이 먼저 -> 상대한테 입금)
	public void transfer(Bank other, long money) {	// 위에 입금방법출금방법 다 있음
		// 내 통장에서 출금된 금액만큼 상대 계좌에 입금한다.
		// long withdrawalMoney = withdrawal(money);
		// other.deposit(withdrawalMoney);
		// 이 두줄을 한줄로 표현하면
		other.deposit(withdrawal(money));
		// ex)출금이 0이면 상대계좌로 0들어감
	
	}
	
	// 4. 계좌 정보 확인
	public void accInfo() {
		System.out.println("계좌번호 : " + accNo + ", 잔액" + balance + "원");
	}
	
	// main
	// 메인 따로 안만들고 여기에 그냥 해도되지만
	public static void main(String[] args) {
		
		Bank me = new Bank("1234", 50000);
		Bank mom = new Bank("4567", 100000);
		
		mom.transfer(me, 50000);
		
		
		me.accInfo();
		mom.accInfo();
		
	}
	
}

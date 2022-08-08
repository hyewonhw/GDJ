package Prac_0729;

public class BankAccount {
	
	// 클래스 공부하고 다시풀어보기
	
	private String accNo;
	private long balance;
	
	public BankAccount(String accNo, long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	// 입금
	public void deposit(long money) {
		if(money < 0)
			return;
		balance += money;
	}

	// 출금 
	public boolean withdraw(long money) {
		if(money < 0 || money > balance)
			return false;
		balance -= money;
		return true;
	}
	
	// 이체
	public void transfer(BankAccount other, long money) {
		// 출금 후 입금 순서로 동작해야 함
		// 출금 성공해야 입금도 할 수 있음
		// other : 입금
		// this : 출금
		if(this.withdraw(money))
			other.deposit(money);
	}	
	
	// 조회
	public void inquiry() {
		System.out.println("계좌번호 : " + accNo + ", 잔고 : " + balance + "원");
	}
	
}

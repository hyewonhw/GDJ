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
	
	
	public void transfer(BankAccount other, long money) {
		if(this.withdraw(money))
			other.deposit(money);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

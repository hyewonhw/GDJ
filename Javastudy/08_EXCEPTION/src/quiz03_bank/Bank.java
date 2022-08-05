package quiz03_bank;

public class Bank {
	// 마이너스 입금 불가, 코드 값 1000
	// 마이너스 출금 불가, 코드 값 2000
	// 잔액보다 큰 출금 불가, 코드 값 2001
	
	private String accNo;
	private long balance;
	
	public Bank(String accNo, long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	public void deposit(long money) throws BankException {
		if(money < 0) {
			throw new BankException("마이너스 입금불가", 1);
		}
		balance += money;
	}
	
	public long withdrawal(long money) throws BankException {
		if(money < 0 ) {
			throw new BankException("마이너스 출금 불가", 2);
		} else if (balance < money) {
			throw new BankException("잔액 부족", 3);
		}
		balance -= money;
		return money;
	}
	public void transfer(Bank acc, long money) throws BankException{
		acc.deposit(withdrawal(money));		// deposit, withdrawal둘다 예외 발생해서 throws B.E붙여줌
	}
	
	public void inquiry() {
		System.out.println(this);	
	}

	@Override			// 얘 this
	public String toString() {
		return "Bank [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	
	
	
	
	
	
	
	

}

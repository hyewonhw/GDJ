package quiz03_bank;

public class BankException extends Exception{

	static final long serialVersionUID = -3387516993124229948L;
	private int errorCode;
	
	public BankException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
}

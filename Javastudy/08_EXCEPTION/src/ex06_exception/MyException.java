package ex06_exception;

// 사용자가 정의한 예외 클래스
// Exception 클래스를 상속 받는다

// Serializable 인터페이스 : 이 인터페이스를 구현하면 직렬화가 가능. serialVersionUID 값을 가져야 함(추천)
//   ↑
// Throwable 클래스 : serialVersionUID 값이 필요함
//   ↑
// Exception 클래스 : serialVersionUID 값이 필요함
//   ↑
// MyException 클래스 : serialVersionUID 값이 필요함 

public class MyException extends Exception{

	private static final long serialVersionUID = -5261587893161529333L;
	
	private int errorCode;

	public MyException(String message, int errorCode) {
		super(message);		// message저장은 Exception이(super) 
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	
	
}

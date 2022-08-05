package ex11_static;

public class MyMath {
	
	// static요소
	// 1. 정적 요소
	// 2. 객체생성 이전에 메모리에 미리 만들어 지는 공유 요소
	// 3. 클래스에서 1개만 만들어 짐
	// 4. 클래스를 이용해서 호출하기 때문에 클래스 변수, 클래스 메소드라고 부름
	
	// 필드
	public static final double PI = 3.141592;	// final은 변경 불가능해서 private가아닌 public
	// public static final 이 3개는 세트임
	

	// 메소드
	public static int abs(int n) {			// 절대값 반환하는 메소드
		return (n >= 0 ) ? n : -n;
	}
	
	public static int pow(int a,int b) {
		// a의 b제곱 반환
		// for문 구현
		int result = 1;
		
		for(int cnt = 0; cnt < b; cnt++){
			result *= a;
		}
		return result;
	}

	
	
	
}

package ex01_one_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 배열(Array)
		// 1. 여러 개의 변수를 하나의 이름으로 관리하는 자료 구조
		// 2. 구성 요소
		//		1) 인덱스 : 각 변수의 위치 정보. 0으로 시작
		//		2) 배열명 : 모든 변수를 관리하는 하나의 이름
		// 3. 각 변수는 배열명에 대괄호[]와 인덱스를 붙여서 구분
		
		
		// 배열 선언 및 생성
		// 1. 배열 선언
		// 		int[] arr;		-> int타입이 아니라 int배열타입임
		// 		int arr[];		->둘다 동일하게 동작하지만 위에거를 권장함 
		// 2. 배열 생성
		// 		arr = new int[3]   -> 3개의 변수를 만들어서 관리하겠다
		// 3. 배열 선언 및 생성
		// 		int[] arr = new int[3];    3개의 정수배열  3개 다 arr이라고 부를 수 있음
		
		
		// 배열 요소
		// 1. 배열로 관리되는 각각의 변수
		// 2. 모든 배열 요소의 호출
		//		arr[0]		-> 첫 번째 변수
		//		arr[1]		-> 두번째
		//		arr[2]		-> 세번째 / 이름은 통일되고 번호로 구분
		// 3. 배열 요소는 자동으로 초기화 된다.(어떤 값을 가진다.) /일반변수는 값을 가지지않아 초기화되지않음
		//		값이 없을을 의미하는 0, 0.0, false, null 값을 가진다. /string에서 ""는 빈문자열값이 있는것 null이 값이 없는 것
		
		
		// 배열의 장점
		// * 변수 3개가 있는 상황
		// 일반 변수				배열
		// int a, b, c;				int[] arr = new int[3];
		// System.out.println(a);	for(int i = 0; i < 3; i++){
		// System.out.println(b);		System.out.println(arr[i]);
		// System.out.println(c);	}
	
		
		int[] arr = new int [3];              // 인덱스는 i > j > k
		
		for(int i = 0; i < 3; i++) {
			System.out.println(arr[i]);
		}
		
		arr[0] = 100;
		arr[1] = 50;
		arr[2] = 80;
		
		for(int i = 0; i < 3; i++) {
			System.out.println(arr[i]);
		}
		
		
		// 학생 3명의 점수라고 생각하고 평균 구하기
		int total = 0;
		for(int i = 0; i < 3; i++) {
			total += arr[i];
		}
		double average = (double)total / 3;          // 3으로 나누려면 double 캐스팅해줘야함, average가 double이기때문
		//double average = total / 3.0;            	 // 캐스팅 하지 않으려면 숫자를 3.0으로 상수표기
		
		System.out.println("평균 : " + average + "점");
		
		// 위에서 int[] arr = new int [3]; 이기때문에 숫자 3이 5로 바뀌면 for문 안 모든 3을 5로 바꿔줘야함
		// 종속됐다고함 
		// 3을 길이로 바꾸기
		// 3 -> arr.length;
		// 이렇게되면 double average = total / 3.0;  이 코드는 쓸 수 없음 (실무에서 안쓰는 코드)
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

package ex01_one_dim;

import java.util.Arrays;

public class Ex03_array {

	public static void main(String[] args) {
		
		// 배열의 데이터타입
		// int[] : 참조 타입 (Reference type)

		/*
		 
		 int[] arr = [ 10000, 20000, 30000];
		 
	       |-------|
	 arr   | 0x123 |     // 0x123이 참조타입
	       |-------|
	       |  ...  |
	       |-------|
	arr[0] | 10000 | 0x123
	       |-------|
	arr[1] | 20000 | 
	       |-------|
	arr[2] | 30000 | 
	       |-------|
		
		-> index는 떨어진 (상대적인)위치를 나타냄, 1만큼떨어져있다, 2만큼 떨어져있다
		
		 */	
		
		// 배열의 길이 늘리기
		// 1. 배열의 길이는 변경할 수 없다.
		// 2. 늘어난 길이의 새로운 배열을 만들고, 
		//    기존 배열의 값을 모두 새로운 배열로 옮기고,
		//    기존 배열의 참조값을 새로운 배열의 참조값으로 수정한다.
		
		
		// 길이가 5인 배열을 사용하다가
		// 길이가 1000인 배열로 바꾸기
		
		int[] arr = {1, 2, 3, 4, 5};
		
		// 늘어난 길이의 새로운 배열 만들기
		int[] temp = new int[1000];
		
		//기존 배열의 값을 모두 새로운 배열로 옮기기
		System.arraycopy(arr, 0, temp, 0, arr.length);  // arr,0에있는걸 temp,0에 옮기는게 시작이다, arr.length만큼 옮겨라
		
		// 기존 배열의 참조값을 새로운 배열의 참조값으로 수정하기
		arr = temp;
		
		// 이제 arr 배열의 길이는 1000이다.
		System.out.println(arr.length);
		System.out.println(Arrays.toString(arr));    // 알아서 배열을 문자열로 만들어서 출력해라
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

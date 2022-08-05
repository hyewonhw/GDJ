package ex01_one_dim;

public class Ex02_array {

	public static void main(String[] args) {
		
		// 배열의 초기화
		// int [] arr = new int[] {10, 20, 30, 40, 50};
		int[] arr = {10, 20, 30, 40, 50};
		
		// 최대값/최소값 구하기 (값이 0 ~ 100 사이라는 가정)
		int max = 0;
		int min = 100;                         // 최소값의 시작은 반대값을 가져야함 
		
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
			if(min > arr[i]) {
				min = arr[i];
			}
		}
		System.out.println("최대 : " + max);
		System.out.println("최소 : " + min);
		
		
		// 최대값/최소값 구하기 (값의 바운더리가 정해지지않았다는 가정)
		int maxx = arr[0];
		int minn = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(maxx < arr[i]) {
				maxx = arr[i];
			}
			if(minn > arr[i]) {
				minn = arr[i];
			}
		}
		System.out.println("최대 : " + maxx);
		System.out.println("최소 : " + minn);
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}

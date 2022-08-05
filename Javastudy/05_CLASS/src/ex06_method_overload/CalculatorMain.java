package ex06_method_overload;

public class CalculatorMain {

	public static void main(String[] args) {
		
		Calculator calculator = new Calculator();
		
		System.out.println(calculator.add(1, 1));
		System.out.println(calculator.add(1, 1, 1));
		System.out.println(calculator.add(1, 1, 1, 1));
		
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(calculator.add(arr)); // 15
		
		// 여기arr 넘겨서 전파일arr 이라는 이름으로 받았지만 각각 다른 변수
		// 내용은 같아(같은 참조값)서 이름을 같게해줌 = 값의 전달
		// 전달과정에서 정신을 잃지 않으려면 이름을 같게 가주는 것도 방법
		
		
	}

}

package ex07_constructor;

public class ComputerMain {

	public static void main(String[] args) {
		
		Computer myCom = new Computer("gram", 150);
		myCom.printComputerStatus();
		
		
		Computer yourCom = new Computer();
		yourCom.printComputerStatus();
		// 인수가 비어있기때문에 매개변수가없는 생성자를 찾음
		// 매개변수없는 생성자가 없으면 실행이 불가능함
		// 가능하게 하려면 오버로드 
		// 생성자에 매개변수가 비어있는 생성자를 하나 더 만들어주면 가능
		
		
	}

}

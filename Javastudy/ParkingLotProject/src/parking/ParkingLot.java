package parking;

import java.util.Scanner;

public class ParkingLot {
	
	// 필드(선언)
	private String name;	// 주차장이름
	private Car[] cars;		// 차량 배열
	private int idx;		// 배열의 인덱스
	private Scanner sc;		// 스캐너
	
	public ParkingLot(String name) {
		this.name = name;
		sc = new Scanner(System.in);
		cars = new Car[10];
	}
	
	public void addCar() {
		if(idx==cars.length) {
			System.out.println("더 이상 차량 등록이 불가능합니다.");
			return;		// 메소드 종료
		}
		
		System.out.println("현재 등록된 차량 " + idx + "대");
		System.out.println("차량번호 >>> ");
		String carNo = sc.next();	// 공백안받음
		
		System.out.println("모델 >>> ");
		String model = sc.next();
		Car carinfo = new Car(carNo, model);
		cars[idx++] = carinfo;
		System.out.println("차량번호 " + carNo + "차량이 등록되었습니다.");
	}// method
	
	
	public void deleteCar() {
		if(idx == 0) {
			System.out.println("등록된 차량이 없습니다.");
			return;		// 메소드 종료
		}
		System.out.println("제거할 차량번호 >>> ");
		String carNo = sc.next();	// 공백안받음
		for(int i = 0; i < idx; i++) {
			if(cars[i].getCarNo().equals(carNo)) {
				System.arraycopy(cars, i + 1, cars, i, idx - i - 1);	// 삭제
				cars[--idx] = null;
				System.out.println("차량번호 " + carNo + " 차량이 삭제되었습니다.");
				return;		// 메소드종료
			}// if
		}// for
		System.out.println("대상 차량이 존재하지 않습니다.");
	}// method
	
	
	public void printAllCars() {
		if(idx == 0) {
			System.out.println("등록된 차량이 없습니다.");
			return; 		// 메소드 종료
		}
		// 주차장 이름
		System.out.println(name + " 차량목록");
		// 저장된 모든 차량 정보 출력
		for(int i = 0; i < idx; i++) {
			System.out.println(cars[i]);
		}// for	
	} // method
	
	
	public void manage() {
		while(true) {
			System.out.print("1.추가   2.삭제   3.전체   0.종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: addCar();break;
			case 2: deleteCar();break;
			case 3: printAllCars();break;
			case 0: System.out.println("프로그램을 종료합니다.");
					return;
			default : System.out.println("존재하지 않는 메뉴입니다.");
			}// switch
		}// while
	}// method

}// class

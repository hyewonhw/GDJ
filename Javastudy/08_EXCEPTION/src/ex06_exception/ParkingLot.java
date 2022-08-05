package ex06_exception;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class ParkingLot {
	
	private Car[] cars;
	private int idx;
	private Scanner sc;

	
	public ParkingLot() {
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	public void addCar() throws MyException {	// 소속이 Runtime 아래가 아니기 때문에 throws 붙여줘야함
		if(idx == cars.length) {
			throw new MyException("FULL" , 1000);	 // 에러코드
		}
	}
	
	public void deleteCar() throws MyException {
		if(idx == 0) {
			throw new MyException("EMPTY", 2000);	
		}
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.println("1. 추가  2. 삭제  3. 종료  >>> ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: addCar();break;
				case 2: deleteCar();break;
				case 3: return;
				default : throw new RuntimeException("Bad Request");
				}
			} catch (MyException e) {
				System.out.println(e.getMessage()+e.getErrorCode());
			} catch(InputMismatchException e) {
				sc.next();
				System.out.println("처리 명령은 정수만 가능");
			}catch (RuntimeErrorException e) {
				System.out.println(e.getMessage());
			}	
	}
}
		
	
	
	
	public static void main(String[]args) {
		new ParkingLot().manage();	
	}
	
	
 }
	

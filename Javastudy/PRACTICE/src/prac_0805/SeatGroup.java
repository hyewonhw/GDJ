package prac_0805;

import java.util.Scanner;

public class SeatGroup {
	
	private String seatType;	// "S", "R", "A"
	private Seat[] seats;		// 좌석은 예약자가 선택하는것이기때문에 idx 필요없음
	private Scanner sc;
	
	public SeatGroup(String seatType, int cnt) {		// new SeatGroup("S",10); 일때 의자를 놓을 자리는 만들어놨지만 의자를 놓지않은 것 
		this.seatType = seatType;
		seats = new Seat[cnt];
		for(int i = 0; i < cnt; i++) {			// 의자를 만들어주기위한 for문
			seats[i] = new Seat();				// 빈 좌석 가져다 주기 
		}
		sc = new Scanner(System.in);
	}
	
	// 예약(하는 메소드)
	public boolean reserve() {
		reserveInfo();
		// 시트번호는 1부터 시작
		System.out.print("예약할 시트번호 >>> ");
		int seatNo = sc.nextInt();
		if(seatNo < 1 || seatNo > seats.length) {
			System.out.println(seatNo + "번 좌석은 없는 좌석입니다.");
			return false;		// 반환타입이 void가 아니기 때문에 false로 끝내줘야함 
		}
		//예약된 시트인지 확인 - > isOccupied로 확인
		if(seats[seatNo-1].isOccupied()) {
			System.out.println(seatNo + "번 좌석은 없는 좌석입니다.");
			return false;
		}
		// 예약 진행	-> reserve 호출해서 예약
		System.out.println("예약자 이름 >>> ");
		String name = sc.next();
		seats[seatNo-1].reserve(name);
		System.out.println(seatNo + "번 좌석 예약이 완료되었습니다.");
		return true;	
	}
	
	// 예약 취소(하는 메소드)	-> 이름으로 예약 취소할거임
	public boolean cancel() {
		reserveInfo();
		System.out.println("취소자 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < seats.length; i++) {
			// 이름이 있는 좌석(예약된 좌석)만 비교
			if(seats[i].isOccupied()) {	 // 의자에 이름이 있냐를 비교하기위해서 seats[i] != null로는 비교못함 이미 의자 채워놨음
				if(seats[i].isMatched(name)) {	// 예약자 이름과 취소자 이름이 같으면 true 반환
					seats[i].cancel();
					System.out.println("에약자 " + name + "의 예약이 취소되었습니다.");
					return true;
				}
			}
		} // for
		System.out.println(name + "으로 예약된 좌석이 없습니다.");
		return false;
	}
	
	// 예약 상황 출력
	public void reserveInfo() {
		System.out.println("[" + seatType + "]");
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].isOccupied()) {
				System.out.print(seats[i].getName().substring(0,1) + "* ");		// substring(0,1) -> 0에서 1번까지만 = 첫 글자만
			} else {
				System.out.print((i + 1) + (i < 9 ? "   " : "  "));
			}
			if((i + 1) % 10 == 0) {
				System.out.println();
			}
		} // for
		System.out.println();
	}
	

}

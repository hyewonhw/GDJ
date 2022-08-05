package quiz08;

import java.util.Scanner;

public class WeekScheduler {
	
	private int nthWeek; 	// 1 ~ n 주차
	private Day[] week;		// 배열 선언
	private String[] dayNames = {"일", "월","화","수", "목", "금", "토"};
	private Scanner sc; 	// Scanner 선언
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7]; // 배열 생성	
		sc = new Scanner(System.in); // Scanner 생성
	}
	
	private void makeSchedule() {
		System.out.println("▒▒▒ 등록 ▒▒▒");
		System.out.print("요일 입력 >>> ");
		String dayName = sc.next().substring(0,1);
		sc.nextLine();						// 엔터입력 제거용도
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {					// week[i]에 스케줄을 넣어줘야함 / 초기값은 null값
					System.out.print("스케줄 입력 >>> ");
					String schedule = sc.nextLine();		// 스케줄에 공백 입력이 가능함
					Day day = new Day();
					day.setSchedule(schedule);
					week[i] = day;
					System.out.println(dayName + "요일에 새 스케줄이 등록되었습니다.");
				} else {
					System.out.println(dayName + "요일은 이미 스케줄이 있습니다.");
				}
				return; 				// 등록 해도 끝, 등록 못 해도 끝 으로 만들어주기위해 if,else 끝나는시점에 return으로 끝내버림
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");			// for문이 끝난 시점에 	
	}
	
	private void changeSchedule() {
		System.out.println("▒▒▒ 수정 ▒▒▒");
		System.out.println("변경할 요일 입력 >>> ");
		String dayName = sc.next().substring(0,1);
		sc.nextLine();	
		for(int i= 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케쥴이 없습니다.");
					System.out.println("새 스케줄을 등록할까요? (y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);		// yes나 no라고 입력해도 실행할수있게
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")){
						System.out.print("새 스케줄 입력 >>> ");
						String schedule = sc.nextLine();
						Day day = new Day();
						day.setSchedule(schedule);
						week[i] = day;
						System.out.println(dayName + "요일에 새 스케줄이 등록되었습니다.");
					} else {
						System.out.println("스케줄 변경이 취소되었습니다.");
						
					}	
					
			} else {		
					System.out.print(dayNames[i] + "요일의 스케줄은 " + week[i].getSchedule() + "입니다.");
					System.out.print("변경할까요? (y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);		// yes나 no라고 입력해도 실행할수있게
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) { 
						System.out.print("변경할 스케줄 입력 >>> ");
						String schedule = sc.nextLine();
						week[i].setSchedule(schedule);			
						System.out.println(dayName + "요일의 스케줄이 변경되었습니다.");
					} else { 
						System.out.println("스케줄 변경이 취소되었습니다.");
					}
				}
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
	}
	
	private void deleteSchedule() {		
		System.out.println("▒▒▒ 삭제 ▒▒▒");
		System.out.print("삭제할 요일 입력 >>> ");
		String dayName = sc.next().substring(0,1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케줄이 없습니다.");
				} else {
					System.out.println(dayName + "요일의 스케줄은 " + week[i].getSchedule() + "입니다.");
					System.out.print("삭제할까요? (y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);		// yes나 no라고 입력해도 실행할수있게
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")){
						week[i] = null;
						System.out.println(dayName + "요일의 스케줄이 취소되었습니다.");
					} else {
						System.out.println("스케줄 삭제가 취소되었습니다.");
					}
				} 
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");		// for문 끝나는 시점
	}
	
	private void printWeekSchedule() {
		System.out.println("▒▒▒ 전체조회 ▒▒▒");
		System.out.println(nthWeek + "주차 스케줄 안내");
		for(int i= 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 - ");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());
		}	
	}
	
	public void manage() {
		while(true) {
			
			System.out.print("1. 등록   2. 수정   3. 삭제   4. 전체조회    0. 종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1 : makeSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : deleteSchedule(); break;
			case 4 : printWeekSchedule(); break;
			case 0 : System.out.println("스케줄러를 종료합니다. "); return;
			default : System.out.println("인식할 수 없는 명령입니다.");
			}	
		}	
	}
}

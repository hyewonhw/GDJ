package quiz03;

public class Bus {

	// 좌석정보(번호, 이름)
	private Seat[] seats;		// 배열은 선언
	private int limit;			// 버스 정원
	
	
	
	// Bus 생성자에서 배열의 생성을 진행해야함
	public Bus(int cnt) {
		seats = new Seat[cnt];					// 이게 배열 생성	// new Bus(25)인 경우 Seat가 25개 생성됨
		limit = cnt;									
		for(int i = 0; i < cnt; i++) {
			seats[i] = new Seat();						
		}
	}									

	
	
	// ride() 메소드
	public void ride(int seatNo, Person person) {
		// 존재하지않는 시트번호 확인
		if(seatNo <= 0 || seatNo > limit) {
			return; // ride() 메소드 종료
		}
		
	
		// 시트에 사람이 없으면, 시트번호에 탑승한 Person 저장하기
		Seat seat = seats[seatNo-1]; //이게 좌석이고
		Person p = seat.getPerson();		// 시트에 앉아있는 사람을 빼는게 겟펄슨
		if(p == null) {
			seat.setPerson(person);
		}
		
		//if(seats[seatNo - 1 ] == null) {		// 한 좌석에 두명 앉을수 없기 때문에 시트번호 중복 검사
		
		//	seats[seatNo -1].setPerson(person);// 시트번호는 1 ~ 25이기 때문에 배열은 0 ~ 24로 만들어 줘야해서 -1
		}
	
	
	
	// info() 메소드
	public void info() {								
		for(int i = 0; i < limit; i++) {				// limit은 seats배열의 length와 같음
			Seat seat = seats[i];					 
			Person person = seat.getPerson();	// 좌석에 앉아있는 사람빼기			//Person person = seats[i].getPerson();	
			if(person != null) {		// if(seat.getPerson() != null), if(seats[i].gePerson() != null;
				System.out.println((i + 1) + "," + person.getName());
				// Person person = seat.getPerson(); 없애고
				// System.out.println((i + 1) + "," + person.getName());
				// System.out.println((i + 1) + "," + seats[i].getPerson().getName());
			} else {
				System.out.println((i + 1) + ", 비어 있음");
			}
		}
	}
	
	
	
	
}
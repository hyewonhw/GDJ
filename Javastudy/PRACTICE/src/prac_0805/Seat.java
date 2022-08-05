package prac_0805;

public class Seat {

	private String name; // 시트 예약한 사람이름

	// 예약한 사람 확인
	public String getName() {
		return name;
	}
	// 예약 - > 실제 setName이 하는 일이지만 이름이 매치가 안되므로 reserve로 변경해줌
	public void reserve(String name) {
		this.name = name;
	}
	// 예약 취소
	public void cancel() {
		name = null;
	}
	// 예약 여부 확인
	public boolean isOccupied() {
		return name != null; // null 이 아니면 true 반환 -> 에약되어있으면 true 반환
	}
	// 예약자 확인 -> 예약한 사람의 이름과 저장된 이름을 비교하기위함  받아온이름과 저장된이름이 일치하는가
	public boolean isMatched(String name) {
		return name.equals(this.name);	// 받아온이름 name 저장된이름 this.name
	}
	
}

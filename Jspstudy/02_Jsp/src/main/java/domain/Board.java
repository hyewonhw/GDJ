package domain;

public class Board {

	private int boardNO;
	private String title;
	private int hit;
	
	public Board() {
		
	}
	public Board(int boardNO, String title, int hit) {
		super();
		this.boardNO = boardNO;
		this.title = title;
		this.hit = hit;
	}
	
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
		
}

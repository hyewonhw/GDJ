package com.gdu.app03.domain;

public class Board {

	// field
	private int BoardNo;
	private String title;
	private String createDate;
	
	// constructor
	public Board() {
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, String title, String createDate) {
		super();
		BoardNo = boardNo;
		this.title = title;
		this.createDate = createDate;
	}

	// getter/setter
	public int getBoardNo() {
		return BoardNo;
	}
	public void setBoardNo(int boardNo) {
		BoardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}

package com.gdu.app03.domain;

public class Notice {

	// field
	private int noticeNo;
	private String title;
	
	// constructor
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeNo, String title) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
	}
	
	// getter/setter
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

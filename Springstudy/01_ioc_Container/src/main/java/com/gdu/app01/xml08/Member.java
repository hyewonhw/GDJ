package com.gdu.app01.xml08;

import java.util.List;

public class Member {
	
	// field
	private String name;
	private List<String> course;
	private double height;
	private double weigth;
	private BMICalculator bmiCalc;
	
	// constructor
	public Member(String name, List<String> course, double height, double weigth, BMICalculator bmiCalc) {
		super();
		this.name = name;
		this.course = course;
		this.height = height;
		this.weigth = weigth;
		this.bmiCalc = bmiCalc;
	}
	
	// info() 메소드
	public void info() { // 이름 등록과정
		System.out.println("Name :" + name);
		for(String str : course) {
			System.out.println("등록과정 :" + course);			
		}
		System.out.println("키 : " + height);
		System.out.println("몸무게 : " + weigth);
		bmiCalc.info();
	}
	
}

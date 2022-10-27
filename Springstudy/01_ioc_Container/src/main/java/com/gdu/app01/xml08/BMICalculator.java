package com.gdu.app01.xml08;

public class BMICalculator {
	
	// 
	private Calculator calc;
	private double height;
	private double weight;
	private double bmi;
	private String health;

	// constructor
	public BMICalculator(Calculator calc, double height, double weight) {
		super();
		this.calc = calc;
		this.height = height;
		this.weight = weight;
		this.bmi = calc.div(weight, calc.div(calc.mul(height, height), 10000));
		this.health = (bmi < 20) ? "저체중" : (bmi < 25) ? "정상" : (bmi < 30) ? "과체중" : "비만";
	}
	
	// info() 메소드
	public void info() { // 키 몸무게 bmi 건강상태
		System.out.println("bmi : " + bmi);
		System.out.println("건강상태 " + health);
		
	}
	
	// 몸무게 / 키의 제곱
}

package com.tjoeun.oop;

public class CellPhone extends Electronics {
	float screenSize;
	float memory;
	
	public CellPhone() {
		super();
		System.out.println("자식 기본 생성자 실행합니다.");
	}

	public CellPhone(String name, int price, String color, float weight, float screenSize, float memory) {
		super(name, price, color, weight);
		this.screenSize = screenSize;
		this.memory = memory;
		System.out.println("자식 오버로딩 생성자 실행합니다.");
	}
	
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.printf("%f\t%f\n", screenSize, memory);
	}
}

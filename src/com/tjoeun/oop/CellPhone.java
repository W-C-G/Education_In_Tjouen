package com.tjoeun.oop;

public class CellPhone extends Electronics {
	float screenSize;
	float memory;
	
	public CellPhone() {
		super();
		System.out.println("�ڽ� �⺻ ������ �����մϴ�.");
	}

	public CellPhone(String name, int price, String color, float weight, float screenSize, float memory) {
		super(name, price, color, weight);
		this.screenSize = screenSize;
		this.memory = memory;
		System.out.println("�ڽ� �����ε� ������ �����մϴ�.");
	}
	
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.printf("%f\t%f\n", screenSize, memory);
	}
}

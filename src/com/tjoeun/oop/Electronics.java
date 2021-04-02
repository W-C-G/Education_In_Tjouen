package com.tjoeun.oop;

public class Electronics {
	String name;
	int price;
	String color;
	float weight;
	
	public Electronics() {
		System.out.println("부모 기본 생성자 실행합니다.");
	}

	public Electronics(String name, int price, String color, float weight) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.weight = weight;
		System.out.println("부모 오버로딩 생성자 실행합니다.");
	}
	
	public void printInfo() {
		System.out.printf("%s\t%d\t%s\t%f\t\n", name, price, color, weight);
	}
}

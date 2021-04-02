package com.tjoeun.oop;

public class Electronics {
	String name;
	int price;
	String color;
	float weight;
	
	public Electronics() {
		System.out.println("�θ� �⺻ ������ �����մϴ�.");
	}

	public Electronics(String name, int price, String color, float weight) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.weight = weight;
		System.out.println("�θ� �����ε� ������ �����մϴ�.");
	}
	
	public void printInfo() {
		System.out.printf("%s\t%d\t%s\t%f\t\n", name, price, color, weight);
	}
}

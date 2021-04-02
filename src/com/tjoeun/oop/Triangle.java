package com.tjoeun.oop;

public class Triangle extends Shape {
	public Triangle(){
		super();
	}
	
	public Triangle(int length, int height){
		super();
		setLength(length);
		setHeight(height);
	}
	
	@Override
	public void getArea() {
		System.out.println("Triangle Area: "+ (getLength()*getHeight())/2);
	}
}

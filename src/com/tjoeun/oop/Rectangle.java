package com.tjoeun.oop;

public class Rectangle extends Shape {
	public Rectangle(){
		super();
	}
	
	public Rectangle(int length, int height){
		super();
		setLength(length);
		setHeight(height);
	}
	@Override
	public void getArea() {
		System.out.println("Rectangle Area: "+ (getLength()*getHeight()));
	}
}

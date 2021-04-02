package com.tjoeun.oop;

import java.math.*;

public class Circle extends Shape {
	private int radius;
	
	public Circle(){
		super();
	}
	public Circle(int radius){
		super();
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	@Override
	public void getArea() {
		System.out.println("Circle Area: "+ radius*radius*Math.PI);
	}
	
}

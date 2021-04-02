package com.tjoeun.oop;

public class Shape implements IArea{
	private int length;
	private int height;
	
	public Shape(){	}
	public Shape(int length, int height){
		setLength(length);
		setHeight(height);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void getArea() {}
	
}

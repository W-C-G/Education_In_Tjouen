package com.tjoeun.oop;

public class Student {
	static String schoolName = "���δ��б�";
	String name;
	
	// Constructor
	Student(){
		
	}

	// Constructor
	Student(String name){
		this.name = name;
	}
	
	public void printName() {
		System.out.printf("School Name: %s\nStudent Name: %s", Student.schoolName, name);
	}
	
}

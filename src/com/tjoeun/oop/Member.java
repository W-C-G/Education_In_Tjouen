package com.tjoeun.oop;

import java.io.Serializable;

public class Member implements Serializable{
	private int number;
	private String name;
	private String phone;
	private String email;
	
	public Member(){
		
	}
	
	public Member(int number, String name, String phone, String email){
		setNumber(number);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void printList() {
		System.out.printf("%d\t%s\t%s\t%s\n", number, name, phone, email);
	}
	
	@Override
	public String toString() {
		return String.format("%, %", this.name, this.email);
	}

	@Override
	public boolean equals(Object obj) {
		Member b = (Member)obj;
		return (this.name.equals(b.name)) && (this.number == b.number);
	}
}

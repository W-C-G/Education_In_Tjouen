package com.tjoeun.jdbc;

/**
 * 
 * @author WooCheol Kwon
 * 회원정보 저장을 위한 클래스
 */

public class MemberVO {
	private int num;
	private String name;
	private String phone;
	private String email;
	
	MemberVO(){}
	MemberVO(int num, String name, String phone, String email){
		setNum(num);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s", num, name, phone, email);
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
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
	
	
}

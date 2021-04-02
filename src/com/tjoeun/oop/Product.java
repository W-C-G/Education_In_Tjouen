package com.tjoeun.oop;

public class Product {
	static String country = "Korea";
	String pname;
	int price;
	String maker;
	String pdate;
	
	// constructor
	Product(){
	}
	
	// constructor overloading
	Product(String pname, int price, String maker, String pdate){
		this.pname = pname;
		this.price = price;
		this.maker = maker;
		this.pdate = pdate;
	}
	
	public void printinfo() {
		System.out.printf("%s\t%s\t%d\t%s\t%s\n", country, this.pname, this.price, this.maker, this.pdate);
	}
	
	public static void printCountry() {
		System.out.println(Product.country);
	}
}

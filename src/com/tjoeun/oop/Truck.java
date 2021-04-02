package com.tjoeun.oop;

public class Truck extends Vehicle {
	int weight;
	
	Truck(){
		super();
	}
	Truck(int unkeep_expenses, int distance_driven, int weight){
		super(unkeep_expenses, distance_driven);
		this.weight = weight;
	}
	
	@Override
	public int calculate_money() {
		int money = super.calculate_money();
		money = distance_driven * 10 * weight;
		return money;
	}
}

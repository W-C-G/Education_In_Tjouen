package com.tjoeun.oop;

public class Taxi extends Vehicle {
	
	Taxi(){
		super();
	}
	Taxi(int unkeep_expenses, int distance_driven){
		super(unkeep_expenses, distance_driven);
	}
	
	@Override
	public int calculate_money() {
		int money = super.calculate_money();
		money = this.distance_driven*20;
		return money;
	}
}

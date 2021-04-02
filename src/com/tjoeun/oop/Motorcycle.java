package com.tjoeun.oop;

public class Motorcycle extends Vehicle {
	int time;
	
	Motorcycle(){
		super();
	}
	
	
	Motorcycle(int unkeep_expenses, int distance_driven, int time){
		super(unkeep_expenses, distance_driven);
		this.time = time;
	}
	
	@Override
	public int calculate_money() {
		int money = super.calculate_money();
		money = time*100;
		return money;
	}
}

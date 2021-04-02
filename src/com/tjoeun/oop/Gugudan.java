package com.tjoeun.oop;

public class Gugudan {
	int inputNum;
	
	Gugudan(){
		
	}
	
	Gugudan(int inputNum){
		this.inputNum = inputNum;
	}
	
	public void show() {
		for(int i=1; i<= 9; i++) {
			System.out.printf("%d * %d = %d\n", inputNum, i, inputNum * i);
		}
	}
}

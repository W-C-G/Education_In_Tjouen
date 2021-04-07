package com.tjoeun.thread;

import java.util.*;

public class ThreadMain {
	public static void main(String[] args) {
		/*
		 * Thread : Virtual CPU
		 * Codes : Implement Runnable
		 * Override Run() Method
		 * 
		 * Thread 객체에게 Codes 전달 => Thread.start();
		 */
		/*
		ThreadMain.DateThread dateThread = new ThreadMain().new DateThread();
		ThreadMain.NumThread numThread = new ThreadMain().new NumThread();
		dateThread.start();
		numThread.start();
		*/
		
		// Parent class = Thread 인 nameless class(익명클래스, Anonymous Class)
		// 익명 클래스의 목적: 1회 실행
		// 아래의 익명클래스가 할 수 있는 것: Thread 상속, Override, 객체 생성
		
		/*new Thread() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.println(new Date());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		*/
		
		// code 1개를 만들어서 cpu 2개에 할당하여 실행한 것
		Runnable r1 = new Runnable1();
		new Thread(r1).start();
		new Thread(r1).start();
	}
	static class Runnable1 implements Runnable{
		int num;
		
		@Override
		public void run() {
			while(true) {
				num++;
				String name = Thread.currentThread().getName();
				System.out.println(name+ ":" + num);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	class DateThread extends Thread{
		@Override
		public void run() {
			// Only, This code can run on the Virtual Cpu(Thread)
			for(int i=0;i<100;i++) {
				System.out.println(new Date());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	class NumThread extends Thread{
		@Override
		public void run() {
			for(int i=0;i<100;i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}
		
	}
}



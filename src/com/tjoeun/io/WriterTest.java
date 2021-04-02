package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class WriterTest {

	public static void main(String[] args) {
		/*
		 * 스트림 클래스들은 내부에서 버퍼(memory)를 사용하여 데이터를
		 * 메모리에 먼저 기록하고 버퍼가 채워지면 그 때 목적지에 보내는 방법을 사용한다.
		 * 
		 * 이유: 디스크에 매번 접속하면 성능이 떨어지므로 메모리에 기록하여
		 * 데이터를 모아 한꺼번에 디스크를 보내는 방법을 사용하여 성능을 올리려고 한다.
		 * 
		 * 만약, 버퍼가 채워지지 않으면 디스크로 보내지 않기 때문에 적은 데이터는 파일에
		 * 기록되지 않을 수 있다. 그러나 마지막에 close()를 호출하면 먼저 버퍼에 있는
		 * 데이터가 목적지에 보내지고 모든 리소스를 해제하게 된다.
		 * 
		 */
		try {
			Scanner sc1 = new Scanner(System.in);
			FileWriter fw = new FileWriter("C:/labs/sample.txt");
			
			String[] name = new String[3];
			for(int i =0;i<name.length;i++) {
				System.out.printf("Input Name: ");
				name[i] = sc1.nextLine();
				fw.write(String.format("%s\n", name[i]));				
			}
			
			fw.close();
			
			FileReader fr = new FileReader("C:/labs/sample.txt");
			Scanner sc = new Scanner(fr);
			
			while(true) {
				String line = sc.nextLine();
				System.out.println(line);	
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException ne) {
			//
		}
		
		System.out.println("프로그램 종료...");
		
	}

}

package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class WriterTest {

	public static void main(String[] args) {
		/*
		 * ��Ʈ�� Ŭ�������� ���ο��� ����(memory)�� ����Ͽ� �����͸�
		 * �޸𸮿� ���� ����ϰ� ���۰� ä������ �� �� �������� ������ ����� ����Ѵ�.
		 * 
		 * ����: ��ũ�� �Ź� �����ϸ� ������ �������Ƿ� �޸𸮿� ����Ͽ�
		 * �����͸� ��� �Ѳ����� ��ũ�� ������ ����� ����Ͽ� ������ �ø����� �Ѵ�.
		 * 
		 * ����, ���۰� ä������ ������ ��ũ�� ������ �ʱ� ������ ���� �����ʹ� ���Ͽ�
		 * ��ϵ��� ���� �� �ִ�. �׷��� �������� close()�� ȣ���ϸ� ���� ���ۿ� �ִ�
		 * �����Ͱ� �������� �������� ��� ���ҽ��� �����ϰ� �ȴ�.
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
		
		System.out.println("���α׷� ����...");
		
	}

}

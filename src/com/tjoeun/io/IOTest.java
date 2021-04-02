package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class IOTest {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("C:/labs/name.txt");
			
			// �� �پ� read
			/*Scanner sc = new Scanner(fr);
			String line = sc.nextLine();
			System.out.println(line);
			sc.close();*/
			
			// �� ���ھ� read
			/*int c = fr.read();
			if(c != -1) {
				char ch = (char)c;
				System.out.println(ch);
			}*/
			
			// ������ ���ڼ���ŭ read
			/*char[] buf = new char[5];
			int cnt = fr.read(buf);
			System.out.println(new String(buf));*/
			
			// �ؽ�Ʈ ���Ͽ��� �� ���ھ� �о ���
			/*while(true) {
				int c = fr.read();
				if(c == -1) break;
				System.out.print((char)c);
			}
			fr.close();*/
			
			// �ټ� ���� ���ڸ� �о ��� �ؽ�Ʈ�� ���
			/*while(true) {
				char[] buf = new char[4];
				int cnt = fr.read(buf);
				if(cnt == -1) break;
				System.out.println(new String(buf, 0, cnt));
			} fr.close();*/
			
			//Scanner�� �̿��� �ؽ�Ʈ ������ ��ü ���� ȭ�鿡 ǥ��
			Scanner sc = new Scanner(fr);
			while(true){
				String line = sc.nextLine();
				System.out.println(line);
			}
			
			/**
			 * ������ �� = -1, null, Exception
			 */

		} catch (NoSuchElementException e) {
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}

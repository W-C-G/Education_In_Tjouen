package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class IOTest {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("C:/labs/name.txt");
			
			// 한 줄씩 read
			/*Scanner sc = new Scanner(fr);
			String line = sc.nextLine();
			System.out.println(line);
			sc.close();*/
			
			// 한 글자씩 read
			/*int c = fr.read();
			if(c != -1) {
				char ch = (char)c;
				System.out.println(ch);
			}*/
			
			// 정해진 글자수만큼 read
			/*char[] buf = new char[5];
			int cnt = fr.read(buf);
			System.out.println(new String(buf));*/
			
			// 텍스트 파일에서 한 글자씩 읽어서 출력
			/*while(true) {
				int c = fr.read();
				if(c == -1) break;
				System.out.print((char)c);
			}
			fr.close();*/
			
			// 다수 개의 글자를 읽어서 모든 텍스트를 출력
			/*while(true) {
				char[] buf = new char[4];
				int cnt = fr.read(buf);
				if(cnt == -1) break;
				System.out.println(new String(buf, 0, cnt));
			} fr.close();*/
			
			//Scanner를 이용해 텍스트 파일의 전체 행을 화면에 표시
			Scanner sc = new Scanner(fr);
			while(true){
				String line = sc.nextLine();
				System.out.println(line);
			}
			
			/**
			 * 파일의 끝 = -1, null, Exception
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

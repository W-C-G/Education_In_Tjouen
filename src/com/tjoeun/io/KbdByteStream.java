package com.tjoeun.io;

import java.io.*;

public class KbdByteStream {

	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		while(true) {
			System.out.print("���� Ű �Է�: ");
			String line;
			try {
				line = br.readLine();
				System.out.println(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

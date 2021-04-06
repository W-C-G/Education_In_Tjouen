package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class StringStream {
	/*
	 * StringReader / StringWriter <- Node Stream
	 * 키보드로부터 한 행을 읽어서 메모리에 저장, 작업을 반복
	 * x를 누르면 지금까지 메모리에 누적된 문자열을 화면에 표시
	 */
	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		// 2Byte를 받아서 (숫자->문자)문자화 시켜줌
		InputStreamReader isreader = new InputStreamReader(System.in);
		
		while(true) {
			int line;
			try {
				// 문자가 하나의 정수로 들어가있음.
				line = isreader.read();
				if((char)line == 'x') break;
				pw.print((char)line);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		}
		System.out.println(sw);
		
	}

}

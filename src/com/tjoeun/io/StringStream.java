package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class StringStream {
	/*
	 * StringReader / StringWriter <- Node Stream
	 * Ű����κ��� �� ���� �о �޸𸮿� ����, �۾��� �ݺ�
	 * x�� ������ ���ݱ��� �޸𸮿� ������ ���ڿ��� ȭ�鿡 ǥ��
	 */
	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		// 2Byte�� �޾Ƽ� (����->����)����ȭ ������
		InputStreamReader isreader = new InputStreamReader(System.in);
		
		while(true) {
			int line;
			try {
				// ���ڰ� �ϳ��� ������ ������.
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

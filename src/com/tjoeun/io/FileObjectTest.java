package com.tjoeun.io;

import java.io.*;
import java.util.Arrays;

public class FileObjectTest {

	public static void main(String[] args) {
		File labsDir = new File("C:/labs/");
		boolean b = labsDir.exists();
		if(b) System.out.println("���͸��� �����մϴ�.");
		else {
			System.out.println("���͸��� �������� �ʽ��ϴ�.");
			return ;
		}
		String[] list = labsDir.list();
//		for(var i:list) {
//			System.out.println(i);
//				
//		}
		String a = Arrays.toString(list);

		System.out.println(a);
	}

}

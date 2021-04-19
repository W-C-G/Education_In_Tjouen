package com.tjoeun.io;

import java.io.*;
import java.util.Arrays;

public class FileObjectTest {

	public static void main(String[] args) {
		File labsDir = new File("C:/labs/");
		boolean b = labsDir.exists();
		if(b) System.out.println("디렉터리가 존재합니다.");
		else {
			System.out.println("디렉터리가 존재하지 않습니다.");
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

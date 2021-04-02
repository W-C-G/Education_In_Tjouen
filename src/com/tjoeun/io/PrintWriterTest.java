package com.tjoeun.io;

import java.io.*;

public class PrintWriterTest {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PrintWriter pw = new PrintWriter("C:/labs/sample.txt");
		pw.println("Hello");
	}

}

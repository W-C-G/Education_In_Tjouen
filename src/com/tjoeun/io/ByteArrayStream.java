package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class ByteArrayStream {

	/*
	 * 1. txt 파일 읽기
	 * 2. FileInputStream으로 읽기
	 * 3. 행단위 읽기
	 * 4. ByteArrayOutputStream에 저장
	 * 5. 3, 4번 반복
	 * 6. 파일 끝에서 프로그램 종료, ByteArrayOutputStream 데이터 화면 출력
	 * 7. 누적된 데이터를 문자열로 변환하여 화면에 한 행씩 출력하기 위한 스트림도 구성해야 함
	 */
	public static void main(String[] args) {
		String fpath = "C:/labs/sample.txt";
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		//OutputStreamWriter osw = new OutputStreamWriter(ba);
		PrintWriter pw = new PrintWriter(ba);
		
		try {
			// file 한 행씩 읽어오기
			fi = new FileInputStream(fpath); // byte
			is = new InputStreamReader(fi);  // 변환 스트림(byte->문자)
			br = new BufferedReader(is);     // 문자
			
			String line = null;
			//ba = new ByteArrayOutputStream();
			while(true) {
				line = br.readLine();
				if(line == null) break;
				pw.println(line);
				pw.flush();
			}
			br.close();
			pw.close();
			
			// 누적된 데이터를 문자열로 변환하여 화면에 한 행씩 출력하기 위한 스트림도 구성해야 함.
			byte[] barr = ba.toByteArray();
			ByteArrayInputStream bain = new ByteArrayInputStream(barr);
			InputStreamReader isr2 = new InputStreamReader(bain);
			BufferedReader br2 = new BufferedReader(isr2);
			while(true) {
				line = br2.readLine();
				if(line == null) break;
				System.out.println(line);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료...");
		
	}

}

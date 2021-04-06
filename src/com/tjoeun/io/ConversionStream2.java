package com.tjoeun.io;

import java.io.*;

public class ConversionStream2 {
	/*
	 * [변환스트림 구성하기]
	 * 키보드에서 입력되는 데이터를 Scanner를 사용하지 않고
	 * 다른 스트림을 사용하여 입력 받아서 최종적으로
	 * FileOutputStream을 사용하여 누적하여 저장해보기.
	 * 키보드에서 입력되는 한 행은 텍스트 파일에서 한 행으로 저장되도록 해보세요.
	 * 
	 * 키보드 입력 -> InputStream(ByteStream) -> ConversionStream
	 * 
	 */
	public static void main(String[] args) {
		String fpath = "C:/labs/input_test.txt";
		
		// InputStreamReader isr = new InputStreamReader(System.in);
		// BuffredReader br = new BufferedReader(isr);
		
		FileOutputStream fout = null;
		OutputStreamWriter osw = null;
		// PrintWriter pw = null;
		// String line = null;
		
		// System.in으로 입력받은 문자를 BufferedInputStream에 저장
		BufferedInputStream bufin = new BufferedInputStream(System.in);
			
		try {
			fout = new FileOutputStream(fpath); // 2Byte씩 쓰는 바이트 스트림
	        osw = new OutputStreamWriter(fout); // 입력한 것을 문자화시켜 저장시켜줌
	        // pw = new PrintWriter(osw);
	        
	        while(true) {
				int line = bufin.read();
				// line = br.readLine().trim();
				if((char)line == 'x') break;
				// if(line.equalsIgnoreCase("x") break;
				// pw.println(line);
				// pw.flush();
				
				System.out.println(line);
				osw.write((char)line);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				osw.close();
				fout.close();
		        bufin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

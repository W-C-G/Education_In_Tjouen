package com.tjoeun.io;

import java.io.*;

public class ConversionStream2 {
	/*
	 * [��ȯ��Ʈ�� �����ϱ�]
	 * Ű���忡�� �ԷµǴ� �����͸� Scanner�� ������� �ʰ�
	 * �ٸ� ��Ʈ���� ����Ͽ� �Է� �޾Ƽ� ����������
	 * FileOutputStream�� ����Ͽ� �����Ͽ� �����غ���.
	 * Ű���忡�� �ԷµǴ� �� ���� �ؽ�Ʈ ���Ͽ��� �� ������ ����ǵ��� �غ�����.
	 * 
	 * Ű���� �Է� -> InputStream(ByteStream) -> ConversionStream
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
		
		// System.in���� �Է¹��� ���ڸ� BufferedInputStream�� ����
		BufferedInputStream bufin = new BufferedInputStream(System.in);
			
		try {
			fout = new FileOutputStream(fpath); // 2Byte�� ���� ����Ʈ ��Ʈ��
	        osw = new OutputStreamWriter(fout); // �Է��� ���� ����ȭ���� ���������
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

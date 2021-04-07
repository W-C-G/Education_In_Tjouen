package com.tjoeun.io;

import java.io.*;
import java.util.*;

public class ByteArrayStream {

	/*
	 * 1. txt ���� �б�
	 * 2. FileInputStream���� �б�
	 * 3. ����� �б�
	 * 4. ByteArrayOutputStream�� ����
	 * 5. 3, 4�� �ݺ�
	 * 6. ���� ������ ���α׷� ����, ByteArrayOutputStream ������ ȭ�� ���
	 * 7. ������ �����͸� ���ڿ��� ��ȯ�Ͽ� ȭ�鿡 �� �྿ ����ϱ� ���� ��Ʈ���� �����ؾ� ��
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
			// file �� �྿ �о����
			fi = new FileInputStream(fpath); // byte
			is = new InputStreamReader(fi);  // ��ȯ ��Ʈ��(byte->����)
			br = new BufferedReader(is);     // ����
			
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
			
			// ������ �����͸� ���ڿ��� ��ȯ�Ͽ� ȭ�鿡 �� �྿ ����ϱ� ���� ��Ʈ���� �����ؾ� ��.
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
		
		System.out.println("���α׷� ����...");
		
	}

}

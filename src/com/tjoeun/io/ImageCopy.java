package com.tjoeun.io;

import java.util.*;
import java.io.*;

public class ImageCopy {

	static String imgPath = "C:/labs/�Ȱ�����.png";
	
	public static void main(String[] args) {
		/*
		 *  txt file -> FileReader, FileWriter : �� ����(2byte, 16bit)
		 *  => character stream
		 *  
		 *  etc.. -> FileInputStream(Source Stream)
		 *  		, FileOutputStream(Sink Stream): 1byte
		 *  => byte stream
		 *  => Node Stream(����: Source Stream, Sink Stream)
		 *  	: ���� ���� ����� ��Ʈ��(Ű����, �����), ���� ���
		 *  	Source Stream: �ҽ�(�� ������)�� �о���� ��� ��Ʈ��
		 *  	Sink Stream: ���������� �����ϴ� ��� ��Ʈ��
		 *  
		 *  => Filter Stream(����: BufferedInputStream, BufferedOutputStream, Scanner, PrintWriter)
		 *  
		 */
		//imageCopy01(); // �ܼ� ����
		
		//imageCopy02(); // byte buffer Ȱ�� ����
		
		//imageCopy03(); // file ��ü Ȱ�� ���� + byte buffer Ȱ�� ����
		
		imageCopy0405(); // byte data load �� ���� ����
		System.out.println("���α׷� ����...");

	}
	
	/*
	 * ByteArrayInputStream, ByteArrayOutputStream Ȱ�� ���� �޼���
	 * ����Ʈ �����͸� �ε��Ͽ� ����Ʈ �迭�� �����Ͽ� �����ϴ� ���
	 */
	private static void imageCopy0405() {
		File f = new File(imgPath);
		if(!f.exists()) return;
		
		String outPath = "C:/labs/�Ȱ�����-������05.png";
		byte[] buf = new byte[1024];

		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		try {
			fin = new FileInputStream(f);
			fout = new FileOutputStream(outPath);
			long start = System.currentTimeMillis();
			
			while(true) {
				int n = fin.read(buf);
				if(n == -1) break;
				//fout.write(buf, 0, n);
				bao.write(buf, 0, n);
			}
			byte[] imgData = bao.toByteArray();
			//System.out.printf("���� ������:%d\t �ε�� ������:%d\n", f.length(), imgData.length);
			
			// �޸𸮿� �ε�� �̹��� �����͸� ��ũ�� ���Ͽ� ����Ͽ� ���ο� ������ ����
//			fout.write(imgData);
//			fout.close();
			
			
			// 1Kb�� ������ �ݺ��Ͽ� ���Ͽ� ����
			ByteArrayInputStream bai = new ByteArrayInputStream(imgData);
			while(true) {
				int n = bai.read(buf);
				if(n == -1) break;
				fout.write(buf,0, n);
			}
			
			bai.close();
			fout.close();
			
			File sinkFile = new File(outPath);
			System.out.printf("���� ������:%d\t �ε�� ������:%d\n", f.length(), sinkFile.length());
			
			
			/*
			 * int i = 0; // �޸𸮸� ��� ���� while(true) { if(i>imgData.length) break; try{
			 * fout.write(imgData, i, 1024); } catch(IndexOutOfBoundsException e) {
			 * fout.write(imgData, i, imgData.length-i); } i+=1024; } fout.close();
			 */
			
			// �����ð�����
			/*long end = System.currentTimeMillis();
			
			System.out.printf("���� ��� �ð�: %d \n", end - start);
			System.out.println("�̹��� ���� ����");*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				//fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*
	 * File ��ü �̿��Ͽ� �����ϴ� �޼���, ���� �޸� ��뷮�� ����
	 */
	private static void imageCopy03() {
		
		File f = new File(imgPath); // file ��ü ���·� load
		if(!f.exists()) return; // file�� �������� �ʴ´ٸ� �Լ� ����
		long flen = f.length(); // file ��ü�� ����
		byte[] buf = new byte[(int) flen];
		
		String outPath = "C:/labs/�Ȱ�����-������03.png";

		// Source Stream
		FileInputStream fin = null;
					
		// Sink Stream
		FileOutputStream fout = null;
					
		try {
			fin = new FileInputStream(f);
			fout = new FileOutputStream(outPath);
			long start = System.currentTimeMillis();
			
			while(true) {
				int n = fin.read(buf);
				if(n == -1) break;
				fout.write(buf, 0, n);
			}
			long end = System.currentTimeMillis();
			
			System.out.printf("���� ��� �ð�: %d \n", end - start);
			System.out.println("�̹��� ���� ����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * byte[] buf ����Ͽ� �ð��� �����ϴ� ���� �޼���
	 */
	private static void imageCopy02() {
		String outPath = "C:/labs/�Ȱ�����-������02.png";

		// Source Stream
		FileInputStream fin = null;
					
		// Sink Stream
		FileOutputStream fout = null;
					
		try {
			fin = new FileInputStream(imgPath);
			fout = new FileOutputStream(outPath);
			long start = System.currentTimeMillis();
			byte[] buf = new byte[1024];
			
			while(true) {
				int n = fin.read(buf);
				if(n == -1) break;
				fout.write(buf, 0, n);
			}
			long end = System.currentTimeMillis();
			
			System.out.printf("���� ��� �ð�: %d \n", end - start);
			System.out.println("�̹��� ���� ����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 *  �ܼ� ���� �޼���
	 */
	private static void imageCopy01() {
		String outPath = "C:/labs/�Ȱ�����-������01.png";

		// Source Stream
		FileInputStream fin = null;
					
		// Sink Stream
		FileOutputStream fout = null;
					
		try {
			fin = new FileInputStream(imgPath);
			fout = new FileOutputStream(outPath);
			long start = System.currentTimeMillis();
			
			while(true) {
				int n = fin.read();
				fout.write(n);
				if(n == -1) break;
			}
			long end = System.currentTimeMillis();
			
			System.out.printf("���� ��� �ð�: %d \n", end - start);
			System.out.println("�̹��� ���� ����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

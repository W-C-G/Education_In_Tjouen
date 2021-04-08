package com.tjoeun.thread;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/*
 * ������ ��¥�� ���Ͽ� �� ������ �� 1�ʸ��� �޸𸮿� ����Ʈ �迭�� �����ϴ� ���ѷ����� �ۼ��ϰ�
 * �� ����Ʈ �迭�κ��� �� �྿ �����ͼ� ȭ�鿡 �� 1�ʸ��� ǥ���ϴ� ����� �ۼ��غ�����.
 * ���� 2���� �۾��� ������ �۵��ؾ� �ϰ�, ���� ������ ���� ���������� ����Ǿ�� �Ѵ�.
 */
public class ThreadPractice {
	static ByteArrayOutputStream bout = new ByteArrayOutputStream();
	static PrintWriter pw = new PrintWriter(bout);
	
//	static byte[] arr = ThreadPractice.bout.toByteArray();
//	static ByteArrayInputStream bin = new ByteArrayInputStream(arr);
//	static InputStreamReader isr = new InputStreamReader(bin);
//	static BufferedReader br = new BufferedReader(isr);
	
	
	public static void main(String[] args) {
		dateInput input = new dateInput();
		dateOutput output = new dateOutput();
		input.start();
		output.start();
	}

}

class dateInput extends Thread{
	
	@Override
	public void run() {
		while(true) {
			String time = new SimpleDateFormat("yyyy�� MM��dd�� HH��mm��ss��").format(new Date());
			ThreadPractice.pw.println(time);
			ThreadPractice.pw.flush();;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class dateOutput extends Thread{
	@Override
	public void run() {
		while(true) { 
			try {
				data_get();
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static void data_get() {
		byte[] arr = ThreadPractice.bout.toByteArray();
		ByteArrayInputStream bin = new ByteArrayInputStream(arr);
		InputStreamReader isr = new InputStreamReader(bin);
		BufferedReader br = new BufferedReader(isr);
		String time = null;

		while(true) {
			try {
				time = br.readLine();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(time == null) break;
			System.out.println(time);
			
		}				
	}
}

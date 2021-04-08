package com.tjoeun.thread;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/*
 * 오늘의 날짜를 구하여 한 행으로 매 1초마다 메모리에 바이트 배열로 누적하는 무한루프를 작성하고
 * 그 바이트 배열로부터 한 행씩 가져와서 화면에 매 1초마다 표시하는 기능을 작성해보세요.
 * 위의 2가지 작업은 무한히 작동해야 하고, 서로 간섭이 없이 독립적으로 실행되어야 한다.
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
			String time = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초").format(new Date());
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

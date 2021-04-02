package com.tjoeun.io;

import java.util.*;
import java.io.*;

public class ImageCopy {

	static String imgPath = "C:/labs/안경고양이.png";
	
	public static void main(String[] args) {
		/*
		 *  txt file -> FileReader, FileWriter : 한 글자(2byte, 16bit)
		 *  => character stream
		 *  
		 *  etc.. -> FileInputStream(Source Stream)
		 *  		, FileOutputStream(Sink Stream): 1byte
		 *  => byte stream
		 *  => Node Stream(종류: Source Stream, Sink Stream)
		 *  	: 노드와 직접 연결된 스트림(키보드, 모니터), 말단 노드
		 *  	Source Stream: 소스(원 데이터)를 읽어오는 노드 스트림
		 *  	Sink Stream: 최종적으로 저장하는 노드 스트림
		 *  
		 *  => Filter Stream(종류: BufferedInputStream, BufferedOutputStream, Scanner, PrintWriter)
		 *  
		 */
		//imageCopy01(); // 단순 복제
		
		//imageCopy02(); // byte buffer 활용 복제
		
		//imageCopy03(); // file 객체 활용 복제 + byte buffer 활용 복제
		
		imageCopy0405(); // byte data load 후 누적 저장
		System.out.println("프로그램 종료...");

	}
	
	/*
	 * ByteArrayInputStream, ByteArrayOutputStream 활용 복제 메서드
	 * 바이트 데이터를 로드하여 바이트 배열에 누적하여 저장하는 방법
	 */
	private static void imageCopy0405() {
		File f = new File(imgPath);
		if(!f.exists()) return;
		
		String outPath = "C:/labs/안경고양이-복제본05.png";
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
			//System.out.printf("파일 사이즈:%d\t 로드된 사이즈:%d\n", f.length(), imgData.length);
			
			// 메모리에 로드된 이미지 데이터를 디스크의 파일에 출력하여 새로운 파일을 생성
//			fout.write(imgData);
//			fout.close();
			
			
			// 1Kb로 나누어 반복하여 파일에 저장
			ByteArrayInputStream bai = new ByteArrayInputStream(imgData);
			while(true) {
				int n = bai.read(buf);
				if(n == -1) break;
				fout.write(buf,0, n);
			}
			
			bai.close();
			fout.close();
			
			File sinkFile = new File(outPath);
			System.out.printf("파일 사이즈:%d\t 로드된 사이즈:%d\n", f.length(), sinkFile.length());
			
			
			/*
			 * int i = 0; // 메모리를 끊어서 생성 while(true) { if(i>imgData.length) break; try{
			 * fout.write(imgData, i, 1024); } catch(IndexOutOfBoundsException e) {
			 * fout.write(imgData, i, imgData.length-i); } i+=1024; } fout.close();
			 */
			
			// 생성시간측정
			/*long end = System.currentTimeMillis();
			
			System.out.printf("복사 경과 시간: %d \n", end - start);
			System.out.println("이미지 복사 성공");*/
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
	 * File 객체 이용하여 복제하는 메서드, 가장 메모리 사용량이 많음
	 */
	private static void imageCopy03() {
		
		File f = new File(imgPath); // file 객체 형태로 load
		if(!f.exists()) return; // file이 존재하지 않는다면 함수 종료
		long flen = f.length(); // file 객체의 길이
		byte[] buf = new byte[(int) flen];
		
		String outPath = "C:/labs/안경고양이-복제본03.png";

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
			
			System.out.printf("복사 경과 시간: %d \n", end - start);
			System.out.println("이미지 복사 성공");
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
	 * byte[] buf 사용하여 시간을 단축하는 복제 메서드
	 */
	private static void imageCopy02() {
		String outPath = "C:/labs/안경고양이-복제본02.png";

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
			
			System.out.printf("복사 경과 시간: %d \n", end - start);
			System.out.println("이미지 복사 성공");
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
	 *  단순 복제 메서드
	 */
	private static void imageCopy01() {
		String outPath = "C:/labs/안경고양이-복제본01.png";

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
			
			System.out.printf("복사 경과 시간: %d \n", end - start);
			System.out.println("이미지 복사 성공");
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

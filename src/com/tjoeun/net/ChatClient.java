package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
	static Scanner sc;
	static PrintWriter pw;
	static BufferedReader br;
	static String id = null;

	public static void main(String[] args) {
		
		try {
			sc = new Scanner(System.in);
			Socket soc = new Socket("127.0.0.1",1234);
			

			// 서버로 가는 데이터 스트림 구성
			OutputStream os = soc.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			
			// 서버로부터 오는 데이터 스트림 구성
			InputStream is = soc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			// 서버로부터 접속 성공이라는 메시지 받기
			String sign = br.readLine();
			System.out.println(sign);
			
			// login 과정
			if(!login()) {
				System.out.println("로그인 실패");
			}
			
			// Msg 전송 과정
			String msg;
			while(true) {
				System.out.print("Msg: ");
				msg = sc.nextLine();
				pw.println(id+":"+msg);
				pw.flush();
			}
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("클라이언트 종료");
	}
	
	private static boolean login() {
		System.out.print("ID: ");
		id = sc.nextLine();
		System.out.print("Password: ");
		String password = sc.nextLine();
		
		pw.println(String.format("%s:%s", id, password));
		pw.flush();
		
		String svrMsg = null;
		try {
			svrMsg = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(svrMsg.equals("1")) {
			System.out.println("[클라이언트] 로그인 성공");
			return true;
		}
		else {
			System.out.println("[클라이언트] 로그인 실패");
			return false;
		}
	}
}

package com.tjoeun.net;

import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) {
		// Port 번호는 1024보다 크게 사용해야 함.
		try {
			ServerSocket svrSoc = new ServerSocket(1234);
			Socket soc = null;
			// 접속할 때까지 대기
			while(true) {
				System.out.println("서버 대기 중...");
				soc = svrSoc.accept(); 	
				
				System.out.println(soc);
				
				Client client1 = new Client(soc);
				client1.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("서버 종료...");
	}
}

class Client extends Thread{
	Socket soc = null;
	OutputStream os;
	OutputStreamWriter osw;
	PrintWriter pw;
	
	// Constructor: chatClient를 실행할 때, 자동으로 클라이언트에게 로그인해달라는 메시지를 보낸다.
	Client(Socket soc){
		this.soc = soc;
		try {
			// server -> client로 보낼 string
			os = soc.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			pw.println("로그인해주세요!");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// Client -> Server 로 보낸 메시지를 읽기 위해 실행
		InputStream is;
		InputStreamReader isr;
		BufferedReader br = null;
		
		boolean login_success = false;
		
		String line = null;
		try {
			is = soc.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			line = br.readLine();
			String[] strlist = line.split(":");
			
			if(strlist[0].equals("smith") && strlist[1].equals("1234")) {
				// 로그인 성공이라는 메시지 전송
				login_success = true;
				pw.println("1");
				System.out.println("[서버] 로그인 성공");
			}
			else {
				// 로그인 실패라는 메시지 전송
				pw.println("0");
				System.out.println("[서버] 로그인 실패");
			}
			pw.flush();
			
			while(true) {
				line = br.readLine();
				System.out.println(line);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


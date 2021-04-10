package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {
	// server 내에서 user 정보 교환하기 위해 map 구조 사용
	static HashMap<String, UserIO> userMap = new HashMap<>();
	
	public static void main(String[] args) {
		// Port 번호는 1024보다 크게 사용해야 함.
		try {
			ServerSocket svrSoc = new ServerSocket(1234);
			Socket soc = null;
			
			// 접속할 때까지 대기
			while(true) {
				System.out.println("서버 대기 중...");
				soc = svrSoc.accept(); 	
				// 접속되면 소켓 정보 출력
				System.out.println(soc);
				
				// 쓰레드 실행
				Client client1 = new Client(soc);
				client1.start();
			}
		} catch (IOException e) {
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
			
			if(strlist[0].startsWith("u") && strlist[1].startsWith("1")) {
				// 로그인 성공이라는 메시지 전송
				login_success = true;
				pw.println("1");
				// 이용자 id, UserIO(읽기, 쓰기, 이용자 id)
				ChatServer.userMap.put(strlist[0], new UserIO(br, pw, strlist[0]));
				System.out.println("[서버] 로그인 성공");
			}
			else {
				// 로그인 실패라는 메시지 전송
				pw.println("0");
				System.out.println("[서버] 로그인 실패");
			}
			pw.flush();
			try {
				while(true) {
					String msg = br.readLine();
					// 모든 접속자의 출력스트림에 메시지를 전송한다
					Set<String> keys = ChatServer.userMap.keySet();
					Iterator<String> it = keys.iterator();
					PrintWriter upw = null;
					while(it.hasNext()) {
						String uid = it.next();
						UserIO uio = ChatServer.userMap.get(uid);
						upw = uio.getPw();
						upw.println(msg);
						upw.flush();
					}
					pw.println(msg);
					pw.flush();
				}
			} catch(Exception e) {
				System.err.println("클라이언트 퇴장");
			}
		} catch (IOException e) {
		}
	}
}

class UserIO{
	private BufferedReader br;
	private PrintWriter pw;
	private String uid;
	
	UserIO(){}
	UserIO(BufferedReader br, PrintWriter pw, String uid){
		setBr(br);
		setPw(pw);
		setUid(uid);
	}

	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public BufferedReader getBr() {
		return br;
	}
	
	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	
}
package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {
	// server ������ user ���� ��ȯ�ϱ� ���� map ���� ���
	static HashMap<String, UserIO> userMap = new HashMap<>();
	
	public static void main(String[] args) {
		// Port ��ȣ�� 1024���� ũ�� ����ؾ� ��.
		try {
			ServerSocket svrSoc = new ServerSocket(1234);
			Socket soc = null;
			
			// ������ ������ ���
			while(true) {
				System.out.println("���� ��� ��...");
				soc = svrSoc.accept(); 	
				// ���ӵǸ� ���� ���� ���
				System.out.println(soc);
				
				// ������ ����
				Client client1 = new Client(soc);
				client1.start();
			}
		} catch (IOException e) {
		} 
		System.out.println("���� ����...");
	}
}

class Client extends Thread{
	Socket soc = null;
	
	
	OutputStream os;
	OutputStreamWriter osw;
	PrintWriter pw;
	
	// Constructor: chatClient�� ������ ��, �ڵ����� Ŭ���̾�Ʈ���� �α����ش޶�� �޽����� ������.
	Client(Socket soc){
		this.soc = soc;
		try {
			// server -> client�� ���� string
			os = soc.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			pw.println("�α������ּ���!");
			pw.flush();
		} catch (IOException e) {
		}
	}
	
	@Override
	public void run() {
		// Client -> Server �� ���� �޽����� �б� ���� ����
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
				// �α��� �����̶�� �޽��� ����
				login_success = true;
				pw.println("1");
				// �̿��� id, UserIO(�б�, ����, �̿��� id)
				ChatServer.userMap.put(strlist[0], new UserIO(br, pw, strlist[0]));
				System.out.println("[����] �α��� ����");
			}
			else {
				// �α��� ���ж�� �޽��� ����
				pw.println("0");
				System.out.println("[����] �α��� ����");
			}
			pw.flush();
			try {
				while(true) {
					String msg = br.readLine();
					// ��� �������� ��½�Ʈ���� �޽����� �����Ѵ�
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
				System.err.println("Ŭ���̾�Ʈ ����");
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
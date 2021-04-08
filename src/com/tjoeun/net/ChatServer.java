package com.tjoeun.net;

import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) {
		// Port ��ȣ�� 1024���� ũ�� ����ؾ� ��.
		try {
			ServerSocket svrSoc = new ServerSocket(1234);
			Socket soc = null;
			// ������ ������ ���
			while(true) {
				System.out.println("���� ��� ��...");
				soc = svrSoc.accept(); 	
				
				System.out.println(soc);
				
				Client client1 = new Client(soc);
				client1.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			
			if(strlist[0].equals("smith") && strlist[1].equals("1234")) {
				// �α��� �����̶�� �޽��� ����
				login_success = true;
				pw.println("1");
				System.out.println("[����] �α��� ����");
			}
			else {
				// �α��� ���ж�� �޽��� ����
				pw.println("0");
				System.out.println("[����] �α��� ����");
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


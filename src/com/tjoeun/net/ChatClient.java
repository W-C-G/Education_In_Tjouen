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
			

			// ������ ���� ������ ��Ʈ�� ����
			OutputStream os = soc.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			
			// �����κ��� ���� ������ ��Ʈ�� ����
			InputStream is = soc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			// �����κ��� ���� �����̶�� �޽��� �ޱ�
			String sign = br.readLine();
			System.out.println(sign);
			
			// login ����
			if(!login()) {
				System.out.println("�α��� ����");
			}
			
			// Msg ���� ����
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
		
		System.out.println("Ŭ���̾�Ʈ ����");
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
			System.out.println("[Ŭ���̾�Ʈ] �α��� ����");
			return true;
		}
		else {
			System.out.println("[Ŭ���̾�Ʈ] �α��� ����");
			return false;
		}
	}
}

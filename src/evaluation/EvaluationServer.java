package evaluation;

import java.io.*;
import java.net.*;
import java.util.*;

public class EvaluationServer {
	// Network Connection Part
	static String server_address = "C:/labs/";
	static String[] file_list;
	
	static List<User> user_list;
	
	public static void main(String[] args) {

		// DB ���� ���� ����
		EvaluationUserDAO eudao = new EvaluationUserDAO();
		user_list = eudao.getDBTable();
		// File List ����
		file_list = getFileList();
		
		try {
			// Server Socket ����
			ServerSocket svrSoc = new ServerSocket(1444);
			
			// ���������� Ŭ���̾�Ʈ�κ��� ��ȣ�� ���� �� �ִ� Socket ����
			Socket soc = null;
			
			while(true) {
				System.out.println("���� ��� ��...");
				
				// ������ Ŭ���̾�Ʈ�κ��� ��ȣ�� ������ �� ������ �ӽ� ���Ͽ� �Ҵ�
				soc = svrSoc.accept();
				System.out.println("���� ����!");
				
				// Ȱ���� �����带 ����
				ClientThread client = new ClientThread(soc, user_list, file_list);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Get a File List 
	private static String[] getFileList(){
		File labsDir = new File(server_address);
		if(labsDir.exists()) {
			System.out.println("���͸��� �����մϴ�.");
		}
		else {
			System.out.println("���͸��� �������� �ʽ��ϴ�.");
			return null;
		}
		
		return labsDir.list();
	}
}

class ClientThread extends Thread{
	Socket soc = null;
	List<User> list = null;
	String[] file_list = null;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	// Constructor
	ClientThread(){}
	ClientThread(Socket soc, List<User> list, String[] file_list){
		this.soc = soc;
		this.list = list;
		this.file_list = file_list;
		// Ŭ���̾�Ʈ�� ����(Ŭ���̾�Ʈ���� ����) ������ ���� �� �ְ� Stream ����
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeUTF("[�α������ּ���]");
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void run() {
		/*
		 * 1. �α��� ���� ����
		 * 2. �䱸���� ����
		 */
		// 1.�α��� ���� ����
		if(!login()) return ;
		System.out.println("�α��� ���� ���� �Ϸ�!");
		
		// 2. �䱸���� ����
		// 1) file_list �۽�
		sendFile();
		System.out.println("���� ����Ʈ �۽� �Ϸ�!");
		
		// 2) �ٿ�ε��� ���ϸ��� ����
		// 3) object �۽�
		// 4) �۽� ��� ����
	}
	
	private boolean login() {
		InputStream is;
		try {
			is = soc.getInputStream();
			ois = new ObjectInputStream(is);
			
			String id_pwd = ois.readUTF();
			String[] id_pwd_list = id_pwd.split(":");
			
			for(var i:list) {
				if(i.getUsername().equals(id_pwd_list[0]) && i.getUpass().equals(id_pwd_list[1])) {
					oos.writeUTF("1");
					oos.flush();
					return true;
				}
			}
			oos.writeUTF("0");
			oos.flush();
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void sendFile() {
		OutputStream os;
		InputStream is;
		try {
			os = soc.getOutputStream();
			oos = new ObjectOutputStream(os);
			
			String toString = Arrays.toString(file_list);
			oos.writeUTF(toString);
			oos.flush();
			
			String file_name = ois.readUTF();
			boolean check = false;
			
			for(var i:file_list) {
				if(file_name.equals(i)) {
					check = true;
					break;
				}
			}
			if(!check) {
				System.out.println("ã�� ������ �����ϴ�.");
				return ;
			}
			else {
				System.out.println("ã�� ������ �ֽ��ϴ�.");
			}
			System.out.println("������ ���ϸ�: "+file_name);
			
			FileInputStream fin = new FileInputStream("C:/labs/"+file_name);
			byte[] buf = fin.readAllBytes();
			
			oos.writeObject(buf);
			oos.flush();
			System.out.println(buf);
			System.out.println("���� ���� �Ϸ�!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerObject {
	// �޽����� �����ϱ� ����, ���������� ����� ������ ����� �޽����� ������ �� �ִ� �ڷᱸ���� �ʿ��ϴ�.
	// ����, ������ HashMap �ڷᱸ�� ��ü�� �����Ͽ� �ű⼭ ������ �ְ� ������ ���·� �޽��� ��ȯ�� ����	
	// HashMap<Key, Value>
	static HashMap<String, UserIO> userMap = new HashMap<>();
	
	public static void main(String[] args) {
		/*
		 * txt �޽��� �Ӹ� �ƴ϶� �̹���, ���� � ������ �� �־�� �ϱ� ������
		 * ObjectStream�� Ȱ���Ͽ� ������ ������ ��, Ŭ���̾�Ʈ�� Objectstream�� Ȱ���� ����
		 * 
		 */
		try {
			// Server Socket ����
			ServerSocket svrSoc = new ServerSocket(1414);
			
			// �ӽ� Client Socket ����
			Socket soc = null;
			
			// ������ ������ ��, Ŭ���̾�Ʈ�� ������ ������ ���
			while(true) {
				System.out.println("���� ��� ��...");
				// ������ �����ϸ� ���� ������ soc�� ��ȯ. ��ȯ�� ���� Ŭ���̾�Ʈ�� ����
				soc = svrSoc.accept();
				System.out.println(soc);
				
				// ������ Ŭ���̾�Ʈ�� ������ �����带 ����
				// (������ ������� Ŭ������ ���·� ���� ������ ��)
				ClientThread client = new ClientThread(soc);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

class ClientThread extends Thread{
	// ������ Ŭ���̾�Ʈ�� ������ �ޱ� ���� ���� ����
	Socket soc = null;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	// �α����� ������ ���� ������ �˱� ���� boolean Ÿ�� ���� ����
	boolean check; 
	
	// Constructor(soc(Ŭ���̾�Ʈ)�� ������ �Ķ���ͷ� �ް�, �α��� ������ ȭ�鿡 ���
	// ObjectOutputStream(ȭ�� ��� �� Ŭ���̾�Ʈ�� ���� ������ ���ǳ��� ���)�� ����
	ClientThread(){}
	ClientThread(Socket soc){
		this.soc = soc;
		try {
			// soc�� OutputStream�� �˾ƿ� oos�� ��η� ����(OutputStream -> ObjectOutputStream)
			oos = new ObjectOutputStream(soc.getOutputStream());
			// PrintWriter�� Println = ObjectOutputStream�� writeUTF(������ ���)
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
		 * 2. �޽��� ���� ���� ����
		 *
		 */
		// 1��. �α��� ���� ����
		// login ���ж�� ������ ����(run ����)
		if(!login()) return ;
		System.out.println("�α��� ���� ���� �Ϸ�!");
		
		// 2��. �޽��� ���� ���� ����
		// Ŭ���̾�Ʈ���� ���� ���� �б�
		msgReceiveWrite();
	}
	
	private boolean login() {
		// Ŭ���̾�Ʈ���� ������ �о�� �� �־�� �ϱ� ������, InputStream ����
		try {
			InputStream is = soc.getInputStream();
			ois = new ObjectInputStream(is);
			
			// BufferedReader�� read ���, oin.read Ȱ��
			// String id_pwd: Ŭ���̾�Ʈ���� �Է¹��� id, pwd ���ڿ�
			String id_pwd = ois.readUTF();
			String[] id_pwd_list = id_pwd.split(":");
			if(id_pwd_list[0].startsWith("u") && id_pwd_list[1].startsWith("1")) {
				// Login ����! 
				// �α��� ������ ������ ���� Ŭ���̾�Ʈ���� �˷��ֱ� ���� write���
				// Ŭ���̾�Ʈ �ܿ� �������Ե� �α����� ������ ���� �˷��ֱ�.
				oos.writeUTF("1");
				oos.flush();
				
				// HashMap�� ���� ����
				ChatServerObject.userMap.put(id_pwd_list[0], new UserIO(id_pwd_list[0], ois, oos));
				return true;
			} else {
				oos.writeUTF("0");
				oos.flush();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void msgReceiveWrite() {
		// ChatMsg(�۽���, ������, �޽�������, ����) ��ü�� Ȱ���� �޽��� ����
		ChatMsg msgObj; //Ŭ���̾�Ʈ���� ������ ������ ��ü�� �о���� ���� ����
		try {
			while(true) {
				msgObj = (ChatMsg) ois.readObject();
				
				// �ӼӸ� ������ ����
				String receiver = msgObj.getReceiver();
				if(receiver != null && receiver.length() != 0) {
					System.out.println("���ù��� ���� �ƴմϴ�!");
					UserIO u = ChatServerObject.userMap.get(receiver);
					ObjectOutputStream oos = u.getOos();
					oos.writeObject(msgObj);
					oos.flush();
				}
				
				// ��� �������� ��� ��Ʈ���� �޽����� �����ϴ� ����
				else {
					System.out.println("���ù��� ���Դϴ�!");
					// Server�� �ִ� HashMap���� Key(id)�� ���� ������ ������ �� �ֵ��� iterator ����
					// HashMap���� Key�� ������ �� �ִ� ���� UserIO(id, ois, oos)
					Set<String> ids = ChatServerObject.userMap.keySet();
					Iterator<String> it = ids.iterator();
					
					// ChatMsg ������ �����ڿ��� �۽��ؾ� �ϱ� ������ �߰� ObjectOutputStream ����
					ObjectOutputStream uoos = null;
					while(it.hasNext()) {
						String sender = it.next();
						// uio(id, ois, oos)
						UserIO uio = ChatServerObject.userMap.get(sender);
						
						uoos = uio.getOos();
						// uio�� ����� oos�� �����忡�� �θ� oos�� ���� ��� Ŭ���̾�Ʈ�� �ۼ����� �ʰ� �ѱ��.
						if(uoos == this.oos) continue; 
						uoos.writeObject(msgObj);
						uoos.flush();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// HashMap�� �Է��� ��ü�� �����ϱ� ���� UserIO�� ����.
// HashMap�� �Է��ϴ� ����<Id, UserIO>, UserIO���� id�� ois, oos�� ����.
class UserIO{
	private String id;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	UserIO(){}
	UserIO(String id, ObjectInputStream ois, ObjectOutputStream oos){
		setId(id);
		setOis(ois);
		setOos(oos);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ObjectInputStream getOis() {
		return ois;
	}
	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}
	public ObjectOutputStream getOos() {
		return oos;
	}
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	
	
}
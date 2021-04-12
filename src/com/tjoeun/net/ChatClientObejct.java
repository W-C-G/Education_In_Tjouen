package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.*;

// Q. Ŭ���̾�Ʈ ������ ������ ���, ��Ʈ�� ��ȣ�� ������ �ʴ� ����̾�� �ϱ� ������ �̷� ��� constant�� ����ϸ� ���� �������? Yes
// Q. ip �ּҴ� ���� ��ǻ���� �ּҰ� ���� �ϱ� ������, ������ ����ڿ��� �˷���� �ϴ� ���ŷ����� ���� �� �����ϴ�. 
// ������ ���� ��ǻ���� ip �ּҸ� �־ �������ڴ� ���Ȼ��� ������ ������ �˴ϴ�. ���� �ذ����� �������? ��Ʈ���/��ȭ���� ���� ���� ü���� ��� ����. ���� �ּҰ� �����Ǵ� ���� ���� ��.

// Q. ���� �۽�) FileInputStream�� Ȱ���� ���� ��(byte �迭 ����) �״�� ChatMsg ���� �� �޽��� ������ ������ �Ȱ��� �����Ѵ�.
// 	 ���� ����) FileOutputStream�� Ȱ���� ���� �迭 ObjectOutputStream���� ��ȯ 

public class ChatClientObejct {
	static Scanner sc = new Scanner(System.in);
	static String id;
	public static void main(String[] args) {
		/* �������� ������ �����带 �����ϱ� ���� ���� ���� �� ����
		 * �ٸ� ��ǻ�Ϳ��� ����� ���, localhost�� �ּ� ��� ���� ��ǻ���� ip �ּҰ� ���� �ǰ�,
		 * ��Ʈ�� ��ȣ�� �����ؾ� ��.
		 */
		try {
			Socket soc = new Socket("localhost", 1414);
			/*
			 *  1. �����κ��� �α����ش޶�� ������ �о�;���.(InputStream)
			 *  2. �������� �α����ϴ� ������ �����ؾ� ��. (OutputStream)
			 *  3. �α��� �����ߴٸ�, �����ߴٴ� ������ �о�;� ��. (InputStream)
			 *  4. �޽��� ���� ���� ����(OutputStream) 
			 */
			// 1. �����κ��� �α����ش޶�� ������ �о�;���.
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			String server_Msg = ois.readUTF();
			System.out.println(server_Msg); //"�α������ּ���"
			
			// 2. �������� �α����ϴ� ������ �����ؾ���.
			// 3. �α��� �����ߴٸ�, �����ߴٴ� ������ �о�;���.
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			// login ����
			if(!login(ois, oos)) {
				System.out.println("�α��� ����");
				return ;
			}
						
			//4. �޽��� ���� ���� ����
			//(1) �޽��� ����
			new Thread() {
				@Override
				public void run() {
					String msg;
					String receiver;
					
					try {
						while(true) {
							ChatMsg msgObj = (ChatMsg) ois.readObject();
							msg = msgObj.getMsg();
							receiver = msgObj.getReceiver();
							
							// message�� ������ null => file�� ���������� �޾ƾ���.
							if(msg != null && receiver.length() != 0) {
								System.out.println("\n"+msg);
							} else {
								// ���� �ּҸ� �ۼ��ϴ� �� ���� �ؾ����� ������̶� �׳� �����س��� ���·� �ۼ�
								String hello = "C:\\labs\\hellocat.png";
								FileOutputStream fout_new = new FileOutputStream(hello);
								//ObjectOutputStream new_oos = new ObjectOutputStream(fout_new);
								//new_oos.writeObject(msgObj.getFiledata());
								//new_oos.flush();
								//new_oos.close();
								fout_new.write(msgObj.getFiledata());
								fout_new.close();
								System.out.println("���� ���� �� ���� ����");
							}
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("[Ŭ���̾�Ʈ ������ ����]");
				}
			}.start();
			
			//(2) �޽��� �۽�
			new Thread() {
				@Override
				public void run() {
				// (file ���� ����)
				InputStream fis;
				OutputStream fos;
				String msg;
				String fpath;
				while(true) {
					// �бⰡ �������� ����
					// ��ê���� �������� Ȯ���ϰ� ������ �Է¹޾� ���ο� ��ü ���� �� writeobject ����
					System.out.print("�������� ���� ���̶�� 0, ��ü���� ���� ���̶�� 1, ���ο��� ������ �����Ѵٸ� 2�� �����ּ���.");
					String decision1 = sc.nextLine();
					
					ChatMsg msgObj=null;
					
					if(decision1.equals("0")) {
						System.out.print("Receiver: ");
						String receiver = sc.nextLine();
						System.out.print("Input Msg: ");
						msg = sc.nextLine();
						msgObj = new ChatMsg(id, receiver, id+":"+msg); 
					}
					else if(decision1.equals("1")){
						System.out.print("Input Msg: ");
						msg = sc.nextLine();
						msgObj = new ChatMsg(id, null, id+":"+msg);	
					} 
					else if(decision1.equals("2")) {
						System.out.print("���� ���� ��(�ּҸ� �����Ͽ� �ۼ����ּ���): ");
						fpath = sc.nextLine();
						System.out.print("Receiver: ");
						String receiver = sc.nextLine();
						try {
							fis = new FileInputStream(fpath); // byte �迭
							byte[] byteArray = null;
							try {
								byteArray = fis.readAllBytes();
							} catch (IOException e) {
								e.printStackTrace();
							}
							msgObj = new ChatMsg(id, receiver, byteArray);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");
					}
					// msgObj�� ���¿� �°� write�� ���
					try {
						oos.writeObject(msgObj);
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/*
	 *  ����ڿ��� id, pwd �Է¹޾� ������ ����
	 */
	private static boolean login(ObjectInputStream ois, ObjectOutputStream oos) {
		System.out.print("ID: ");
		id = sc.nextLine();
		System.out.print("Password: ");
		String pwd = sc.nextLine();
		
		try {
			// server�� ����
			oos.writeUTF(id+":"+pwd);
			oos.flush();
			
			// ���� ��, �α��� �����ߴ��� ���θ� �ľ�
			String login_check = ois.readUTF(); // ���������� 1, �����ϸ� 0�̶�� ���ڸ� �о��
			if(login_check.equals("1")) {
				System.out.println("�α��� ����");
				return true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}

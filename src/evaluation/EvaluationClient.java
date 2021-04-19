package evaluation;

import java.io.*;
import java.net.*;
import java.util.*;

public class EvaluationClient {
	static ObjectInputStream ois;
	static ObjectOutputStream oos;
	static Scanner sc = new Scanner(System.in);
	static String name;
	
	
	public static void main(String[] args) {

		// Server�� ������ ���� ����
		try {
			Socket soc = new Socket("localhost", 1444);
			/*
			 *  1. �����κ��� �α����ش޶�� ������ �о�;���.(InputStream)
			 *  2. �������� �α����ϴ� ������ �����ؾ� ��. (OutputStream)
			 *  3. �α��� �����ߴٸ�, �����ߴٴ� ������ �о�;� ��. (InputStream)
			 *  4. ���� ���� ����(OutputStream) 
			 */
			// 1. �����κ��� �α����ش޶�� ������ �о�;���.(InputStream)
			ois = new ObjectInputStream(soc.getInputStream());
			String server_Msg = ois.readUTF();
			System.out.println(server_Msg); // [�α������ּ���]
			
			// 2. �������� �α����ϴ� ������ �����ؾ� ��. (OutputStream)
			// 3. �α��� �����ߴٸ�, �����ߴٴ� ������ �о�;� ��. (InputStream)
			oos = new ObjectOutputStream(soc.getOutputStream());
			// login ����
			if(!login(ois, oos)) {
				System.out.println("�α��� ����");
				return ;
			}
			
			
			// 4. ���� ���� ����(OutputStream) 
			
			// 1) file_list ����
			if(!getFilelist(soc)) return ;

			// 2) �ٿ�ε��� ���ϸ��� �۽�
			// 3) object ����
			SFNRF(ois, oos);		
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static boolean login(ObjectInputStream ois, ObjectOutputStream oos) {
		System.out.print("Name: ");
		name = sc.nextLine();
		System.out.print("Password: ");
		String pwd = sc.nextLine();
		
		try {
			// server�� ����
			oos.writeUTF(name+":"+pwd);
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
	
	private static boolean getFilelist(Socket soc) {
		ObjectInputStream ois;
		String file_list;
		
		try {
			ois = new ObjectInputStream(soc.getInputStream());
			file_list = ois.readUTF();
			System.out.println(file_list);
			return true;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		return false;
	}
	
	// Send File Name & Receive File
	private static void SFNRF(ObjectInputStream ois, ObjectOutputStream oos) {
		System.out.print("�ٿ�ε� �� ���� �̸��� �Է����ּ���: ");
		String file_name = sc.nextLine();
		
		try {
			oos.writeUTF(file_name);
			oos.flush();
			
			System.out.println("������ ���Źް� �ֽ��ϴ�...");
			byte[] buf = (byte[]) ois.readObject();
			
			FileOutputStream fout = new FileOutputStream("C:/labs/������"+file_name);
			fout.write(buf);
			fout.close();
			System.out.println("���� ���� �� ���� ����");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

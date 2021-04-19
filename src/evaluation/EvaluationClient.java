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

		// Server로 연결할 소켓 생성
		try {
			Socket soc = new Socket("localhost", 1444);
			/*
			 *  1. 서버로부터 로그인해달라는 정보를 읽어와야함.(InputStream)
			 *  2. 서버에게 로그인하는 정보를 전달해야 함. (OutputStream)
			 *  3. 로그인 성공했다면, 성공했다는 정보를 읽어와야 함. (InputStream)
			 *  4. 이후 과정 수행(OutputStream) 
			 */
			// 1. 서버로부터 로그인해달라는 정보를 읽어와야함.(InputStream)
			ois = new ObjectInputStream(soc.getInputStream());
			String server_Msg = ois.readUTF();
			System.out.println(server_Msg); // [로그인해주세요]
			
			// 2. 서버에게 로그인하는 정보를 전달해야 함. (OutputStream)
			// 3. 로그인 성공했다면, 성공했다는 정보를 읽어와야 함. (InputStream)
			oos = new ObjectOutputStream(soc.getOutputStream());
			// login 실행
			if(!login(ois, oos)) {
				System.out.println("로그인 실패");
				return ;
			}
			
			
			// 4. 이후 과정 수행(OutputStream) 
			
			// 1) file_list 수신
			if(!getFilelist(soc)) return ;

			// 2) 다운로드할 파일명을 송신
			// 3) object 수신
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
			// server로 전송
			oos.writeUTF(name+":"+pwd);
			oos.flush();
			
			// 전송 후, 로그인 성공했는지 여부를 파악
			String login_check = ois.readUTF(); // 성공했으면 1, 실패하면 0이라는 문자를 읽어옴
			if(login_check.equals("1")) {
				System.out.println("로그인 성공");
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
		System.out.print("다운로드 할 파일 이름을 입력해주세요: ");
		String file_name = sc.nextLine();
		
		try {
			oos.writeUTF(file_name);
			oos.flush();
			
			System.out.println("파일을 수신받고 있습니다...");
			byte[] buf = (byte[]) ois.readObject();
			
			FileOutputStream fout = new FileOutputStream("C:/labs/복제본"+file_name);
			fout.write(buf);
			fout.close();
			System.out.println("파일 수신 및 저장 성공");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

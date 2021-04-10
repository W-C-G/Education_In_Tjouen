package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.*;

// Q. 클라이언트 파일을 배포할 경우, 포트의 번호는 변하지 않는 상수이어야 하기 때문에 이럴 경우 constant를 사용하면 좋지 않을까요? Yes
// Q. ip 주소는 서버 컴퓨터의 주소가 들어가야 하기 때문에, 일일이 사용자에게 알려줘야 하는 번거러움이 있을 것 같습니다. 
// 하지만 서버 컴퓨터의 ip 주소를 넣어서 전달하자니 보안상의 이유로 걱정이 됩니다. 좋은 해결방법이 있을까요? 인트라넷/방화벽과 같은 보안 체제로 방어 가능. 서버 주소가 공개되는 것은 흔한 일.

public class ChatClientObejct {
	static Scanner sc = new Scanner(System.in);
	static String id;
	public static void main(String[] args) {
		/* 서버에서 생성한 쓰레드를 연결하기 위해 소켓 생성 및 연결
		 * 다른 컴퓨터에서 사용할 경우, localhost의 주소 대신 서버 컴퓨터의 ip 주소가 들어가면 되고,
		 * 포트의 번호는 일정해야 함.
		 */
		try {
			Socket soc = new Socket("localhost", 1414);
			/*
			 *  1. 서버로부터 로그인해달라는 정보를 읽어와야함.(InputStream)
			 *  2. 서버에게 로그인하는 정보를 전달해야 함. (OutputStream)
			 *  3. 로그인 성공했다면, 성공했다는 정보를 읽어와야 함. (InputStream)
			 *  4. 메시지 전달 과정 수행(OutputStream) 
			 */
			// 1. 서버로부터 로그인해달라는 정보를 읽어와야함.
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			String server_Msg = ois.readUTF();
			System.out.println(server_Msg); //"로그인해주세요"
			
			// 2. 서버에게 로그인하는 정보를 전달해야함.
			// 3. 로그인 성공했다면, 성공했다는 정보를 읽어와야함.
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			// login 실행
			if(!login(ois, oos)) {
				System.out.println("로그인 실패");
				return ;
			}
			
			//4. 메시지 전달 과정 수행
			//(1) 메시지 수신
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
							System.out.println("\n"+msg);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("[클라이언트 쓰레드 종료]");
				}
			}.start();
			
			//(2) 메시지 송신
			new Thread() {
				@Override
				public void run() {
				String msg;
				while(true) {
					// 분기가 나눠지는 지점
					// 전챗일지 갠톡일지 확인하고 수신자 입력받아 새로운 객체 생성 후 writeobject 실행
					System.out.print("개인톡을 보낼 것이라면 0을 전체톡을 보낼 것이라면 1을 눌러주세요.");
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
					} else {
						System.out.println("번호를 잘못 입력하셨습니다.");
					}
					// msgObj의 형태에 맞게 write를 사용
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
	 *  사용자에게 id, pwd 입력받아 서버로 전송
	 */
	private static boolean login(ObjectInputStream ois, ObjectOutputStream oos) {
		System.out.print("ID: ");
		id = sc.nextLine();
		System.out.print("Password: ");
		String pwd = sc.nextLine();
		
		try {
			// server로 전송
			oos.writeUTF(id+":"+pwd);
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
	
}

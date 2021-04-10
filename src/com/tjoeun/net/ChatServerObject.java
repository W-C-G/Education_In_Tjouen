package com.tjoeun.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerObject {
	// 메시지를 전달하기 위해, 서버에서는 사용자 정보와 사용자 메시지를 저장할 수 있는 자료구조가 필요하다.
	// 따라서, 서버에 HashMap 자료구조 객체를 생성하여 거기서 정보를 넣고 빼오는 형태로 메시지 교환을 구현	
	// HashMap<Key, Value>
	static HashMap<String, UserIO> userMap = new HashMap<>();
	
	public static void main(String[] args) {
		/*
		 * txt 메시지 뿐만 아니라 이미지, 파일 등도 전달할 수 있어야 하기 때문에
		 * ObjectStream을 활용하여 서버를 구축한 뒤, 클라이언트도 Objectstream을 활용해 구현
		 * 
		 */
		try {
			// Server Socket 생성
			ServerSocket svrSoc = new ServerSocket(1414);
			
			// 임시 Client Socket 생성
			Socket soc = null;
			
			// 서버를 실행한 후, 클라이언트가 접속할 때까지 대기
			while(true) {
				System.out.println("서버 대기 중...");
				// 서버에 접속하면 접속 정보를 soc에 반환. 반환한 것은 클라이언트의 정보
				soc = svrSoc.accept();
				System.out.println(soc);
				
				// 접속한 클라이언트의 정보로 쓰레드를 실행
				// (실행할 쓰레드는 클래스의 형태로 먼저 만들어야 함)
				ClientThread client = new ClientThread(soc);
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

class ClientThread extends Thread{
	// 실행할 클라이언트의 정보를 받기 위해 소켓 생성
	Socket soc = null;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	// 로그인이 성공한 것을 서버가 알기 위해 boolean 타입 변수 생성
	boolean check; 
	
	// Constructor(soc(클라이언트)의 정보를 파라미터로 받고, 로그인 과정을 화면에 출력
	// ObjectOutputStream(화면 출력 및 클라이언트로 보낼 내용을 정의내릴 통로)을 정의
	ClientThread(){}
	ClientThread(Socket soc){
		this.soc = soc;
		try {
			// soc의 OutputStream을 알아와 oos의 통로로 연결(OutputStream -> ObjectOutputStream)
			oos = new ObjectOutputStream(soc.getOutputStream());
			// PrintWriter의 Println = ObjectOutputStream의 writeUTF(문자의 경우)
			oos.writeUTF("[로그인해주세요]");
			oos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		/*
		 * 1. 로그인 과정 수행
		 * 2. 메시지 전달 과정 수행
		 *
		 */
		// 1번. 로그인 과정 수행
		// login 실패라면 쓰레드 종료(run 종료)
		if(!login()) return ;
		System.out.println("로그인 과정 수행 완료!");
		
		// 2번. 메시지 전달 과정 수행
		// 클라이언트에서 보낸 정보 읽기
		msgReceiveWrite();
	}
	
	private boolean login() {
		// 클라이언트에서 서버로 읽어올 수 있어야 하기 때문에, InputStream 제작
		try {
			InputStream is = soc.getInputStream();
			ois = new ObjectInputStream(is);
			
			// BufferedReader의 read 대신, oin.read 활용
			// String id_pwd: 클라이언트에서 입력받은 id, pwd 문자열
			String id_pwd = ois.readUTF();
			String[] id_pwd_list = id_pwd.split(":");
			if(id_pwd_list[0].startsWith("u") && id_pwd_list[1].startsWith("1")) {
				// Login 성공! 
				// 로그인 과정이 성공한 것을 클라이언트에게 알려주기 위해 write사용
				// 클라이언트 외에 서버에게도 로그인이 성공한 것을 알려주기.
				oos.writeUTF("1");
				oos.flush();
				
				// HashMap에 정보 저장
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
		// ChatMsg(송신자, 수진자, 메시지내용, 파일) 객체를 활용해 메시지 전달
		ChatMsg msgObj; //클라이언트에서 서버로 전송한 객체를 읽어오기 위해 생성
		try {
			while(true) {
				msgObj = (ChatMsg) ois.readObject();
				
				// 귓속말 보내는 과정
				String receiver = msgObj.getReceiver();
				if(receiver != null && receiver.length() != 0) {
					System.out.println("리시버가 널이 아닙니다!");
					UserIO u = ChatServerObject.userMap.get(receiver);
					ObjectOutputStream oos = u.getOos();
					oos.writeObject(msgObj);
					oos.flush();
				}
				
				// 모든 접속자의 출력 스트림에 메시지를 전송하는 과정
				else {
					System.out.println("리시버가 널입니다!");
					// Server에 있는 HashMap에서 Key(id)를 통해 정보를 가져올 수 있도록 iterator 생성
					// HashMap에서 Key로 가져올 수 있는 것은 UserIO(id, ois, oos)
					Set<String> ids = ChatServerObject.userMap.keySet();
					Iterator<String> it = ids.iterator();
					
					// ChatMsg 정보를 수신자에게 송신해야 하기 때문에 추가 ObjectOutputStream 제작
					ObjectOutputStream uoos = null;
					while(it.hasNext()) {
						String sender = it.next();
						// uio(id, ois, oos)
						UserIO uio = ChatServerObject.userMap.get(sender);
						
						uoos = uio.getOos();
						// uio에 저장된 oos가 쓰레드에서 부른 oos와 같을 경우 클라이언트에 작성하지 않고 넘긴다.
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

// HashMap에 입력할 객체를 생성하기 위해 UserIO를 제작.
// HashMap에 입력하는 형태<Id, UserIO>, UserIO에는 id와 ois, oos가 포함.
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
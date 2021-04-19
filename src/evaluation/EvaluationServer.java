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

		// DB 연결 정보 추출
		EvaluationUserDAO eudao = new EvaluationUserDAO();
		user_list = eudao.getDBTable();
		// File List 추출
		file_list = getFileList();
		
		try {
			// Server Socket 생성
			ServerSocket svrSoc = new ServerSocket(1444);
			
			// 서버측에서 클라이언트로부터 신호를 받을 수 있는 Socket 생성
			Socket soc = null;
			
			while(true) {
				System.out.println("서버 대기 중...");
				
				// 서버가 클라이언트로부터 신호를 얻으면 그 정보를 임시 소켓에 할당
				soc = svrSoc.accept();
				System.out.println("접속 성공!");
				
				// 활동할 쓰레드를 생성
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
			System.out.println("디렉터리가 존재합니다.");
		}
		else {
			System.out.println("디렉터리가 존재하지 않습니다.");
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
		// 클라이언트로 보낼(클라이언트에서 읽을) 내용을 보낼 수 있게 Stream 구성
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
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
		 * 2. 요구사항 실행
		 */
		// 1.로그인 과정 수행
		if(!login()) return ;
		System.out.println("로그인 과정 수행 완료!");
		
		// 2. 요구사항 실행
		// 1) file_list 송신
		sendFile();
		System.out.println("파일 리스트 송신 완료!");
		
		// 2) 다운로드할 파일명을 수신
		// 3) object 송신
		// 4) 송신 결과 수신
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
				System.out.println("찾는 파일이 없습니다.");
				return ;
			}
			else {
				System.out.println("찾는 파일이 있습니다.");
			}
			System.out.println("전송할 파일명: "+file_name);
			
			FileInputStream fin = new FileInputStream("C:/labs/"+file_name);
			byte[] buf = fin.readAllBytes();
			
			oos.writeObject(buf);
			oos.flush();
			System.out.println(buf);
			System.out.println("파일 전송 완료!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

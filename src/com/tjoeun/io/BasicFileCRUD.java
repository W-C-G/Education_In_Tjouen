package com.tjoeun.io;

import java.io.*;
import java.util.*;

import com.tjoeun.exception.SearchException;
import com.tjoeun.io.*;
import com.tjoeun.oop.Member;

public class BasicFileCRUD {

	static String line = "================================================";
	static String menu = "[추가 - a\t" + "검색 - f\t" + "삭제 - d\t" + "수정 - u\t"	+ "목록 - s\t"	+ "종료 - x\t]";
	static Scanner sc = new Scanner(System.in);
	static LinkedList<Member> memlist = new LinkedList<>();
	
	public static void main(String[] args) {
		load();
		while(true) {
			String key = null;
			try {
				key = menu();
				switch(key) {
				case "a":
					add();
					break;
				case "f":
					find().printList();
					System.out.println();
					break;
				case "d":
					delete();
					break;
				case "u":
					update();
					break;
				case "s":
					list();
					break;
				case "x":
					break;
				default: 
					throw new Exception("메뉴 입력 오류");
				}	  
			} catch (SearchException e2) {
				System.err.println(e2.getMessage());
				continue;
			} catch(IndexOutOfBoundsException ioe) {
				System.err.println(ioe.getMessage());
				continue;
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			if(key.equals("x")) {
				System.out.println("프로그램을 종료합니다...");
				break;
			}
		}
				
	}
	
	private static Member find() throws SearchException {
		System.out.println("<찾기 기능을 선택했습니다>");
		
		System.out.println("번호로 찾으려면 m, 이름으로 찾을 때는 n번을 눌러주세요.");
		String key_d = sc.nextLine();
		Member result = null;
		
		switch(key_d) {
		case "m":
			System.out.printf("찾을 번호를 입력하세요: ");
			int search_num = sc.nextInt();
			sc.nextLine();
			for(int i =0; i<memlist.size();i++) {
				result = memlist.get(i);
				if(result.getNumber() == search_num)
					return result;
			}
			String msg = String.format("%s 검색결과가 없습니다.", search_num);
			throw new SearchException(msg);
		case "n":
			System.out.printf("찾을 이름을 입력하세요: ");
			String search_name = sc.nextLine();
			
			for(int i =0; i<memlist.size();i++) {
				result = memlist.get(i);
				if(result.getName().equals(search_name)) {
					return result;
				}
			}
			String msg1 = String.format("%s 검색결과가 없습니다.", search_name);
			throw new SearchException(msg1);
		}
		
		return result;
	}
	
	private static void add() {
		System.out.println("<추가 기능을 선택했습니다>");
		
		int num = 0;
		String name = null;
		String phone = null;
		String email = null;
		
		while(true) {			
			try {
				System.out.println("번호: ");
				num = sc.nextInt();
				
				System.out.println("이름: ");
				name = sc.next();
				
				System.out.println("전화번호: ");
				phone = sc.next();
				
				System.out.println("이메일: ");				
				email = sc.next();
				sc.nextLine();
				break;
			} catch(InputMismatchException ie) {
				System.err.println("번호항목은 숫자만 입력해주세요.");
				sc.nextLine();
			} catch(Exception e) {
				System.err.println("전화번호 형식이 틀렸습니다.");
				sc.nextLine();
			}
		}
		
		
		Member mem1 = new Member();
		mem1.setNumber(num);
		mem1.setName(name);
		mem1.setPhone(phone);
		mem1.setEmail(email);
		
		memlist.add(mem1);
		
		boolean saved = save(mem1, true);
		if(saved) System.err.println("파일에 저장 성공");
		else System.err.println("파일에 저장 중 오류 발생");

		
		System.out.println("[추가, 저장 완료]");
	}

	private static String menu() throws Exception{
		System.out.println(line);
		System.out.println("번호\t이름\t전화번호\t\t이메일");
		System.out.println(line);
		
		for(Member m:memlist) {
			m.printList();
		}
		
		System.out.println();
		System.out.println(menu);
		System.out.print("원하는 기능키를 입력하시오: ");
		String key = sc.nextLine();
		if(key.length()>=2) {
			throw new Exception("메뉴는 1문자로 구성되어야 함");
		}
		return key;
	}
	
	private static void list() {
		System.out.println("<목록 기능을 선택했습니다>");
		System.out.println(line);
		System.out.print("번호\t이름\t전화번호\t\t이메일\n");
		for(Member m:memlist) {
			m.printList();
		}
		System.out.println();
		System.out.println("[결과 출력 완료]");
		System.out.println(line);
	}
	
	private static void load() {
		FileReader fr = null;
		try {
			fr = new FileReader("C:/labs/sample.txt");
		} catch (FileNotFoundException e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}
		Scanner rd = new Scanner(fr);
		try{
			while(true){		
				String line = rd.nextLine();
				String[] info = line.split("\\|");
				Member mem = new Member();
				mem.setNumber(Integer.parseInt(info[0]));
				mem.setName(info[1]);
				mem.setPhone(info[2]);
				mem.setEmail(info[3]);
				memlist.add(mem); 
			}
		} catch (NoSuchElementException e) {
			}
	}

	private static boolean save(Member m, boolean check) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:/labs/sample.txt", check);
			fw.write(String.format("%d|%s|%s|%s\n", m.getNumber(), m.getName(), m.getPhone(), m.getEmail()));				
			fw.close();
			return true;
		} catch (IOException e) {
		} finally {
			try {
				fw.close();
				return true;
			} catch (IOException e) {
			}
		}
		return false;
	}
	
	private static void update() {
		System.out.println("<수정 기능을 선택했습니다>");
		
		System.out.printf("변경할 사용자의 번호를 입력하세요: ");
		int idx = sc.nextInt();
		sc.nextLine();
	
		System.out.println("전화번호를 변경하려면 p, 이메일을 변경할 때는 e번을 눌러주세요.");
		String del_k = sc.nextLine();
		switch(del_k) {
		case "p": 
			System.out.printf("변경할 내용을 입력하세요: ");
			String temp_phone = sc.nextLine();
			for(int i =0; i<memlist.size();i++) {
				Member mem = new Member();
				mem = memlist.get(i);
				if(mem.getNumber() == idx)
					mem.setPhone(temp_phone);
			}
			
			int cnt = 0;
			
			for(Member mm:memlist) {
				if(cnt==0) {
					boolean saved = save(mm, false);
				}
				else {
					boolean saved = save(mm, true);	
				}
				cnt++;
			}
			
			break;
		case "e":
			System.out.printf("변경할 내용을 입력하세요: ");
			String temp_email = sc.nextLine();			
			for(int i =0; i<memlist.size();i++) {
				Member mem = new Member();
				mem = memlist.get(i);
				if(mem.getNumber() == idx)
					mem.setEmail(temp_email);
			}
			
			int cnt2 = 0;
			
			for(Member mm:memlist) {
				if(cnt2==0) {
					boolean saved = save(mm, false);
				}
				else {
					boolean saved = save(mm, true);	
				}
				cnt2++;
			}
			
			break;
		}	
		System.out.println("[변경완료]");
	}
	private static void delete() {
		System.out.println("<추가 기능을 선택했습니다>");
		
		System.out.println("삭제할 번호를 입력하세요.");
		int del_num = sc.nextInt();
		sc.nextLine();
		int idx = -1;
		
		for(int i =0; i<memlist.size();i++) {
			Member mem = new Member();
			mem = memlist.get(i);
			if(mem.getNumber() == del_num)
				idx = i;
		}
		memlist.remove(idx);
		
		int cnt = 0;
		
		for(Member mm:memlist) {
			if(cnt==0) {
				boolean saved = save(mm, false);
			}
			else {
				boolean saved = save(mm, true);	
			}
			cnt++;
		}
		
		System.out.println("[삭제완료]");
		
	}
}

package com.tjoeun.exception;

import java.util.*;
import com.tjoeun.oop.*;
import com.tjoeun.exception.*;

public class ExceptionMain {

	static String line = "================================================";
	static String menu = "[추가 - a\t" + "검색 - f\t" + "삭제 - d\t" + "수정 - u\t"	+ "목록 - s\t"	+ "종료 - x\t]";
	static Scanner sc = new Scanner(System.in);
	static LinkedList<Member> memlist = new LinkedList<>();
	
	static {
		memlist.add(new Member(11, "홍길동", "010-1111-0000", "hong@naver.com"));
		memlist.add(new Member(22, "김민수", "010-2222-3333", "kim@naver.com"));
		memlist.add(new Member(33, "이철수", "011-3344-5965", "lee@naver.com"));
	}
	
	public static void main(String[] args) throws Exception {
		
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
	
	private static String menu() throws Exception{
		System.out.println(menu);
		System.out.print("원하는 기능키를 입력하시오: ");
		String key = sc.nextLine();
		if(key.length()>=2) {
			throw new Exception("메뉴는 1문자로 구성되어야 함");
		}
		return key;
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
				checkPhone(phone);
				
				System.out.println("이메일: ");				
				email = sc.next();
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
		
		System.out.println("[추가 완료]");

	}
	
	private static void list() {
		System.out.println("<목록 기능을 선택했습니다>");
		System.out.println(line);
		System.out.println("번호\t이름\t전화번호\t\t이메일\n");
		for(Member m:memlist) {
			m.printList();
		}
		System.out.println();
	}
	
	private static void checkPhone(String phone) throws Exception{
		String[] tmp = phone.split("-");
		if(tmp[0].length()==3 && tmp[1].length()==4 && tmp[2].length() ==4)
			return ;
		else {
			throw new Exception("번호 형식이 틀립니다.");
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
					mem.setPhone(temp_phone);;
			}
			break;
		case "e":
			System.out.printf("변경할 내용을 입력하세요: ");
			String temp_email = sc.nextLine();			
			for(int i =0; i<memlist.size();i++) {
				Member mem = new Member();
				mem = memlist.get(i);
				if(mem.getNumber() == idx)
					mem.setEmail(temp_email);;
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
		System.out.println("[삭제완료]");
	}
}

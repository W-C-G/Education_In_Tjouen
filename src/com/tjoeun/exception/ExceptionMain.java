package com.tjoeun.exception;

import java.util.*;
import com.tjoeun.oop.*;
import com.tjoeun.exception.*;

public class ExceptionMain {

	static String line = "================================================";
	static String menu = "[�߰� - a\t" + "�˻� - f\t" + "���� - d\t" + "���� - u\t"	+ "��� - s\t"	+ "���� - x\t]";
	static Scanner sc = new Scanner(System.in);
	static LinkedList<Member> memlist = new LinkedList<>();
	
	static {
		memlist.add(new Member(11, "ȫ�浿", "010-1111-0000", "hong@naver.com"));
		memlist.add(new Member(22, "��μ�", "010-2222-3333", "kim@naver.com"));
		memlist.add(new Member(33, "��ö��", "011-3344-5965", "lee@naver.com"));
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
					throw new Exception("�޴� �Է� ����");
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
				System.out.println("���α׷��� �����մϴ�...");
				break;
			}
		}
	}
	
	private static String menu() throws Exception{
		System.out.println(menu);
		System.out.print("���ϴ� ���Ű�� �Է��Ͻÿ�: ");
		String key = sc.nextLine();
		if(key.length()>=2) {
			throw new Exception("�޴��� 1���ڷ� �����Ǿ�� ��");
		}
		return key;
	}
	
	private static void add() {
		System.out.println("<�߰� ����� �����߽��ϴ�>");
		
		int num = 0;
		String name = null;
		String phone = null;
		String email = null;
		
		while(true) {			
			try {
				System.out.println("��ȣ: ");
				num = sc.nextInt();
				
				System.out.println("�̸�: ");
				name = sc.next();
				
				System.out.println("��ȭ��ȣ: ");
				phone = sc.next();
				checkPhone(phone);
				
				System.out.println("�̸���: ");				
				email = sc.next();
				break;
			} catch(InputMismatchException ie) {
				System.err.println("��ȣ�׸��� ���ڸ� �Է����ּ���.");
				sc.nextLine();
			} catch(Exception e) {
				System.err.println("��ȭ��ȣ ������ Ʋ�Ƚ��ϴ�.");
				sc.nextLine();
			}
		}
		
		
		Member mem1 = new Member();
		mem1.setNumber(num);
		mem1.setName(name);
		mem1.setPhone(phone);
		mem1.setEmail(email);
		
		memlist.add(mem1);
		
		System.out.println("[�߰� �Ϸ�]");

	}
	
	private static void list() {
		System.out.println("<��� ����� �����߽��ϴ�>");
		System.out.println(line);
		System.out.println("��ȣ\t�̸�\t��ȭ��ȣ\t\t�̸���\n");
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
			throw new Exception("��ȣ ������ Ʋ���ϴ�.");
		}
	}
	
	private static Member find() throws SearchException {
		System.out.println("<ã�� ����� �����߽��ϴ�>");
		
		System.out.println("��ȣ�� ã������ m, �̸����� ã�� ���� n���� �����ּ���.");
		String key_d = sc.nextLine();
		Member result = null;
		
		switch(key_d) {
		case "m":
			System.out.printf("ã�� ��ȣ�� �Է��ϼ���: ");
			int search_num = sc.nextInt();
			sc.nextLine();
			for(int i =0; i<memlist.size();i++) {
				result = memlist.get(i);
				if(result.getNumber() == search_num)
					return result;
			}
			String msg = String.format("%s �˻������ �����ϴ�.", search_num);
			throw new SearchException(msg);
		case "n":
			System.out.printf("ã�� �̸��� �Է��ϼ���: ");
			String search_name = sc.nextLine();
			
			for(int i =0; i<memlist.size();i++) {
				result = memlist.get(i);
				if(result.getName().equals(search_name)) {
					return result;
				}
			}
			String msg1 = String.format("%s �˻������ �����ϴ�.", search_name);
			throw new SearchException(msg1);
		}
		
		return result;
	}
	
	private static void update() {
		System.out.println("<���� ����� �����߽��ϴ�>");
		
		System.out.printf("������ ������� ��ȣ�� �Է��ϼ���: ");
		int idx = sc.nextInt();
		sc.nextLine();
	
		System.out.println("��ȭ��ȣ�� �����Ϸ��� p, �̸����� ������ ���� e���� �����ּ���.");
		String del_k = sc.nextLine();
		switch(del_k) {
		case "p": 
			System.out.printf("������ ������ �Է��ϼ���: ");
			String temp_phone = sc.nextLine();
			for(int i =0; i<memlist.size();i++) {
				Member mem = new Member();
				mem = memlist.get(i);
				if(mem.getNumber() == idx)
					mem.setPhone(temp_phone);;
			}
			break;
		case "e":
			System.out.printf("������ ������ �Է��ϼ���: ");
			String temp_email = sc.nextLine();			
			for(int i =0; i<memlist.size();i++) {
				Member mem = new Member();
				mem = memlist.get(i);
				if(mem.getNumber() == idx)
					mem.setEmail(temp_email);;
			}
			break;
		}	
		System.out.println("[����Ϸ�]");
	}
	private static void delete() {
		System.out.println("<�߰� ����� �����߽��ϴ�>");
		
		System.out.println("������ ��ȣ�� �Է��ϼ���.");
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
		System.out.println("[�����Ϸ�]");
	}
}

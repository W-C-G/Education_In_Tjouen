package com.tjoeun.collection;

import java.util.*;
import com.tjoeun.oop.*;

public class CollectionMain {

	public static void main(String[] args) {
		/*
		 * ArrayList -> Ž�� ��������, �߰�/���� �Ҹ�
		 * LinkedList -> �߰�/���� ����, Ž�� �Ҹ�
		 * 
		 * list = ���� 0, �ߺ� ���		=> LinkedList<�ڷ���> xx = new LinkedList<>();
		 * 							=> ArrayList<�ڷ���> xx = new ArrayList<>();							
		 * 
		 * set = ���� x, �ߺ� ����		=> HashSet<�ڷ���> xx = new HashSet<>();
		 * map = key-value			=> HashMap<�ڷ���> xx = new HashMap<>();
		 * 
		 * Collection API���� �⺻���� ���� ó������ �ʴ´�.
		 * 
		 * Boxing(�⺻���� ������ ��ü�� ��ȯ�ϴ� ��) ex) int -> Integer
		 * Unboxing(������ ��ü�� �⺻������ ��ȯ�ϴ� ��) ex) Integer -> int
		 * 
		 * API(Application Programming Interface)
		 * = ���α׷��� ������ ����ϴ� ���̺귯���� ����.
		 * 
		 */
		
		String line = "================================================";

		ArrayList<Member> memlist = new ArrayList<>();
		memlist.add(new Member(11, "ȫ�浿", "010-1111-0000", "hong@naver.com"));
		memlist.add(new Member(22, "��μ�", "010-2222-3333", "kim@naver.com"));
		memlist.add(new Member(33, "��ö��", "011-3344-5965", "lee@naver.com"));
		
		
		LinkedList <Member> memlink = new LinkedList<>();
		memlink.add(new Member(11, "ȫ�浿", "010-1111-0000", "hong@naver.com"));
		memlink.add(new Member(22, "��μ�", "010-2222-3333", "kim@naver.com"));
		memlink.add(new Member(33, "��ö��", "011-3344-5965", "lee@naver.com"));
		
		for(Member n: memlink) {
			n.printList();
		}
		System.out.println(line);
		
		HashSet<String> sSet = new HashSet<>();
		sSet.add("�ϳ�");
		sSet.add("��");
		sSet.add("��");
		sSet.add("��");
		sSet.add("�ټ�");
		
		System.out.println(sSet.toString());
		
		Iterator<String> it = sSet.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "First");
		
		map.put("2", "Second");
		
		map.put("3", "Third");
		
		System.out.println(map.get("1"));
	}

}

package com.tjoeun.collection;

import java.util.*;
import com.tjoeun.oop.*;

public class CollectionMain {

	public static void main(String[] args) {
		/*
		 * ArrayList -> 탐색 빠르지만, 추가/삭제 불리
		 * LinkedList -> 추가/삭제 유리, 탐색 불리
		 * 
		 * list = 순서 0, 중복 허용		=> LinkedList<자료형> xx = new LinkedList<>();
		 * 							=> ArrayList<자료형> xx = new ArrayList<>();							
		 * 
		 * set = 순서 x, 중복 불허		=> HashSet<자료형> xx = new HashSet<>();
		 * map = key-value			=> HashMap<자료형> xx = new HashMap<>();
		 * 
		 * Collection API들은 기본형에 대해 처리하지 않는다.
		 * 
		 * Boxing(기본형을 참조형 객체로 변환하는 것) ex) int -> Integer
		 * Unboxing(참조형 객체를 기본형으로 변환하는 것) ex) Integer -> int
		 * 
		 * API(Application Programming Interface)
		 * = 프로그래밍 언어에서는 사용하는 라이브러리를 말함.
		 * 
		 */
		
		String line = "================================================";

		ArrayList<Member> memlist = new ArrayList<>();
		memlist.add(new Member(11, "홍길동", "010-1111-0000", "hong@naver.com"));
		memlist.add(new Member(22, "김민수", "010-2222-3333", "kim@naver.com"));
		memlist.add(new Member(33, "이철수", "011-3344-5965", "lee@naver.com"));
		
		
		LinkedList <Member> memlink = new LinkedList<>();
		memlink.add(new Member(11, "홍길동", "010-1111-0000", "hong@naver.com"));
		memlink.add(new Member(22, "김민수", "010-2222-3333", "kim@naver.com"));
		memlink.add(new Member(33, "이철수", "011-3344-5965", "lee@naver.com"));
		
		for(Member n: memlink) {
			n.printList();
		}
		System.out.println(line);
		
		HashSet<String> sSet = new HashSet<>();
		sSet.add("하나");
		sSet.add("둘");
		sSet.add("셋");
		sSet.add("셋");
		sSet.add("다섯");
		
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

package com.tjoeun.jdbc;

import java.util.List;

import com.tjoeun.exception.SearchException;

/**
 * 
 * @author WooCheol Kwon
 *
 * 이용자와 상호작용하는 CLI 기능의 클래스
 */

public class MySQL_CRUD {

	public static void main(String[] args) {
		while(true) {
			var dao = new MemberDAO();
			String key = null;
			try {
				key = dao.menu();
				switch(key) {
				case "a":
					dao.add(dao.addMem());
					break;
				case "f":
					List<MemberVO> list = dao.find();
					for(var l:list)
						System.out.println(l);
					break;
				case "d":
					dao.delete();
					break;
				case "u":
					dao.update(dao.updateMem());
					break;
				case "s":
					dao.list();
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

}

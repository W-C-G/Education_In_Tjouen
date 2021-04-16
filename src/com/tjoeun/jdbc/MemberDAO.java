package com.tjoeun.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.tjoeun.exception.SearchException;
import com.tjoeun.io.*;
import com.tjoeun.oop.Member;

/**
 * 
 * @author WooCheol Kwon
 *  DB I/O
 */
public class MemberDAO {
	static String line = "================================================";
	static String menu = "[추가 - a\t" + "검색 - f\t" + "삭제 - d\t" + "수정 - u\t"	+ "목록 - s\t"	+ "종료 - x\t]";
	static Scanner sc = new Scanner(System.in);
	
	static String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	static Connection conn;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	private static Connection getConn() {
	    try {
			conn = DriverManager.getConnection(url, "root", "dncjf1357");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	    return null;
	}
	
	private static List<MemberVO> getList(){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "SELECT * FROM member";
		List<MemberVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				list.add(new MemberVO(num, name, phone, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return list;
	}
	
	public static void list() {
		System.out.println(line);
		System.out.print("번호\t이름\t전화번호\t\t이메일\n");
		System.out.println(line);
		
		List<MemberVO> list = getList();
		
		for(var l:list) {
			System.out.println(l);
		}
		
		System.out.println();
		System.out.println(line);
	}
	
	public static String menu() throws Exception{
		list();
		System.out.println(menu);
		System.out.print("원하는 기능키를 입력하시오: ");
		String key = sc.nextLine();
		if(key.length()>=2) {
			throw new Exception("메뉴는 1문자로 구성되어야 함");
		}
		return key;
	}
	
	public static boolean add(MemberVO mem){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "INSERT INTO member(num, name, phone, email) VALUES (?, ?, ?, ?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getNum());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPhone());
			pstmt.setString(4, mem.getEmail());
			
			int n = pstmt.executeUpdate();

			System.out.println("[추가, 저장 완료]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}
	
	public static MemberVO addMem() {
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
	
		return new MemberVO(num, name, phone, email);	
	}

	public static boolean update(MemberVO mem){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "UPDATE member SET name = ?, phone = ?, email = ? WHERE num = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getPhone());
			pstmt.setString(3, mem.getEmail());
			pstmt.setInt(4, mem.getNum());
			
			int n = pstmt.executeUpdate();
			
			System.out.println("[변경완료]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}
	
	public static MemberVO updateMem() {
		System.out.println("<수정 기능을 선택했습니다>");
		
		System.out.printf("변경할 사용자의 번호를 입력하세요: ");
		int idx = sc.nextInt();
		sc.nextLine();
	
		System.out.println("변경할 이름과 전화번호, 이메일을 입력해주세요: ");
		
		String up_name = sc.nextLine();
		String up_phone = sc.nextLine();
		String up_email = sc.nextLine();
		
		return new MemberVO(idx, up_name, up_phone, up_email);		
	}

	public static boolean delete(){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		System.out.println("삭제할 번호를 입력하세요.");
		int del_num = sc.nextInt();
		sc.nextLine();
		
		String sql = "DELETE FROM member WHERE num = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, del_num);

			int n = pstmt.executeUpdate();
			System.out.println("[삭제완료]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}

	
	public static List<MemberVO> findByNum(int num){
		
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "SELECT * FROM member WHERE num = ?";
		
		List<MemberVO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num1 = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				MemberVO result = new MemberVO(num1, name, phone, email);
				list.add(result);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return list;
	}
	
	
	public static List<MemberVO> findByName(String name){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "SELECT * FROM member WHERE name = ?;";
		
		List<MemberVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String name1 = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				MemberVO result = new MemberVO(num, name1, phone, email);
				list.add(result);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return null;
	}
	
	public static List<MemberVO> find() throws SearchException {
		
		System.out.println("번호로 찾으려면 m, 이름으로 찾을 때는 n번을 눌러주세요.");
		String key_d = sc.nextLine();
		
		
		switch(key_d) {
		case "m":
			System.out.printf("찾을 번호를 입력하세요: ");
			int search_num = sc.nextInt();
			sc.nextLine();
			
			return findByNum(search_num);
			
		case "n":
			System.out.printf("찾을 이름을 입력하세요: ");
			String search_name = sc.nextLine();
			
			return findByName(search_name);
		}
		
		return null;
	}
	
	private static void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

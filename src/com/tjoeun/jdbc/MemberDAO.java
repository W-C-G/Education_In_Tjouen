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
	static String menu = "[�߰� - a\t" + "�˻� - f\t" + "���� - d\t" + "���� - u\t"	+ "��� - s\t"	+ "���� - x\t]";
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
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
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
		// ���ҽ� ����
		return list;
	}
	
	public static void list() {
		System.out.println(line);
		System.out.print("��ȣ\t�̸�\t��ȭ��ȣ\t\t�̸���\n");
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
		System.out.print("���ϴ� ���Ű�� �Է��Ͻÿ�: ");
		String key = sc.nextLine();
		if(key.length()>=2) {
			throw new Exception("�޴��� 1���ڷ� �����Ǿ�� ��");
		}
		return key;
	}
	
	public static boolean add(MemberVO mem){
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
		String sql = "INSERT INTO member(num, name, phone, email) VALUES (?, ?, ?, ?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getNum());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPhone());
			pstmt.setString(4, mem.getEmail());
			
			int n = pstmt.executeUpdate();

			System.out.println("[�߰�, ���� �Ϸ�]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// ���ҽ� ����
		return false;
	}
	
	public static MemberVO addMem() {
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
				
				System.out.println("�̸���: ");				
				email = sc.next();
				sc.nextLine();
				break;
			} catch(InputMismatchException ie) {
				System.err.println("��ȣ�׸��� ���ڸ� �Է����ּ���.");
				sc.nextLine();
			} catch(Exception e) {
				System.err.println("��ȭ��ȣ ������ Ʋ�Ƚ��ϴ�.");
				sc.nextLine();
			}
		}
	
		return new MemberVO(num, name, phone, email);	
	}

	public static boolean update(MemberVO mem){
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
		String sql = "UPDATE member SET name = ?, phone = ?, email = ? WHERE num = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getPhone());
			pstmt.setString(3, mem.getEmail());
			pstmt.setInt(4, mem.getNum());
			
			int n = pstmt.executeUpdate();
			
			System.out.println("[����Ϸ�]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// ���ҽ� ����
		return false;
	}
	
	public static MemberVO updateMem() {
		System.out.println("<���� ����� �����߽��ϴ�>");
		
		System.out.printf("������ ������� ��ȣ�� �Է��ϼ���: ");
		int idx = sc.nextInt();
		sc.nextLine();
	
		System.out.println("������ �̸��� ��ȭ��ȣ, �̸����� �Է����ּ���: ");
		
		String up_name = sc.nextLine();
		String up_phone = sc.nextLine();
		String up_email = sc.nextLine();
		
		return new MemberVO(idx, up_name, up_phone, up_email);		
	}

	public static boolean delete(){
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
		System.out.println("������ ��ȣ�� �Է��ϼ���.");
		int del_num = sc.nextInt();
		sc.nextLine();
		
		String sql = "DELETE FROM member WHERE num = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, del_num);

			int n = pstmt.executeUpdate();
			System.out.println("[�����Ϸ�]");
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// ���ҽ� ����
		return false;
	}

	
	public static List<MemberVO> findByNum(int num){
		
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
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
		// ���ҽ� ����
		return list;
	}
	
	
	public static List<MemberVO> findByName(String name){
		// DB�� ����
		conn = getConn();
		// emp ���̺��� ��ü ����Ʈ�� �����´�.
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
		// ���ҽ� ����
		return null;
	}
	
	public static List<MemberVO> find() throws SearchException {
		
		System.out.println("��ȣ�� ã������ m, �̸����� ã�� ���� n���� �����ּ���.");
		String key_d = sc.nextLine();
		
		
		switch(key_d) {
		case "m":
			System.out.printf("ã�� ��ȣ�� �Է��ϼ���: ");
			int search_num = sc.nextInt();
			sc.nextLine();
			
			return findByNum(search_num);
			
		case "n":
			System.out.printf("ã�� �̸��� �Է��ϼ���: ");
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

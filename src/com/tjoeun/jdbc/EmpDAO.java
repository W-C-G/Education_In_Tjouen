package com.tjoeun.jdbc;

import java.sql.*;
import java.util.*;

public class EmpDAO {

	static String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	Connection getConn() {
	    try {
			conn = DriverManager.getConnection(url, "root", "*****");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	    return null;
	}
	
	public List<EmpVO> getList(){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "SELECT * FROM emp";
		List<EmpVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int eno = rs.getInt("eno");
				String ename = rs.getString("ename");
				int deptno = rs.getInt("deptno");
				
				list.add(new EmpVO(eno, ename, deptno));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return list;
	}
	
	public boolean add(EmpVO emp){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = String.format("INSERT INTO emp(eno, ename, deptno) VALUES (%d, '%s', %d);", emp.getEno(), emp.getEname(), emp.getDeptno());
		try {
			stmt = conn.createStatement();
			int n = stmt.executeUpdate(sql);
			
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}
	
	public boolean update(EmpVO emp){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = String.format("UPDATE emp SET ename = '%s', deptno = %d WHERE eno = %d;", emp.getEname(), emp.getDeptno(), emp.getEno());
		try {
			stmt = conn.createStatement();
			int n = stmt.executeUpdate(sql);
			
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}
	
	public boolean delete(int eno){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = String.format("DELETE FROM emp WHERE eno = %d;", eno);
		try {
			stmt = conn.createStatement();
			int n = stmt.executeUpdate(sql);
			
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return false;
	}
	
	private void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public EmpVO findByEno(int eno){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = String.format("SELECT * FROM emp WHERE eno = %d;", eno);
		EmpVO result = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int eno1 = rs.getInt("eno");
				String ename = rs.getString("ename");
				int deptno = rs.getInt("deptno");
				
				result = new EmpVO(eno1, ename, deptno);
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		// 리소스 해제
		return null;
	}
	
	public List findByDeptno(int deptno){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = String.format("SELECT * FROM emp WHERE deptno = %d;", deptno);
		
		List<EmpVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int eno = rs.getInt("eno");
				String ename = rs.getString("ename");
				int deptno2 = rs.getInt("deptno");
				
				EmpVO result = new EmpVO(eno, ename, deptno2);
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
	
	public static void main(String[] args) {
		var dao = new EmpDAO();
		
		System.out.println(dao.add(new EmpVO(6, "Red", 40)));
		
		System.out.println(dao.update(new EmpVO(6, "Blue", 10)));
		
		System.out.println(dao.delete(6));
		
		EmpVO tmp = dao.findByEno(1);
		
		System.out.println(tmp);
		
		List<EmpVO> list = dao.findByDeptno(10);
		
		for(var l:list) {
			System.out.println(l);
		}
	}

}

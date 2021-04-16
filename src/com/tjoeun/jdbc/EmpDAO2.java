package com.tjoeun.jdbc;

import java.sql.*;
import java.util.*;

public class EmpDAO2 {

	static String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Connection getConn() {
	    try {
			conn = DriverManager.getConnection(url, "root", "dncjf1357");
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
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
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
		String sql = "INSERT INTO emp(eno, ename, deptno) VALUES (?, ?, ?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			
			int n = pstmt.executeUpdate();
			
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
		String sql = "UPDATE emp SET ename = ?, deptno = ? WHERE eno = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEname());
			pstmt.setInt(2, emp.getDeptno());
			pstmt.setInt(3, emp.getEno());
			
			int n = pstmt.executeUpdate();
			
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
		String sql = "DELETE FROM emp WHERE eno = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);

			int n = pstmt.executeUpdate();
			
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
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public EmpVO findByEno(int eno){
		// DB에 접속
		conn = getConn();
		// emp 테이블에서 전체 리스트를 가져온다.
		String sql = "SELECT * FROM emp WHERE eno = ?";
		EmpVO result = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);

			
			rs = pstmt.executeQuery();
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
		String sql = "SELECT * FROM emp WHERE deptno = ?;";
		
		List<EmpVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);

			
			rs = pstmt.executeQuery();
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
		var dao = new EmpDAO2();
		
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

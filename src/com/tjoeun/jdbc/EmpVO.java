package com.tjoeun.jdbc;

public class EmpVO {
	private int eno;
	private String ename;
	private int deptno;
	
	EmpVO(){}
	EmpVO(int eno, String ename, int deptno){
		setEno(eno);
		setEname(ename);
		setDeptno(deptno);
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%d", eno, ename, deptno);
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	
}

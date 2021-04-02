package com.tjoeun.oop;

public class Employee {
	private int empno;
	private String ename;
	private int deptno;
	private int salary;
	
	public Employee(int empno, String ename, int deptno, int salary) {
		this.empno = empno;
		this.ename = ename;
		this.deptno = deptno;
		this.salary = salary;
	}

	public Employee() {
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		if(empno<1) {
			System.out.println("직원 번호 입력 오류입니다.");
			return;
		}
		this.empno = empno;
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return String.format("%d, %s", this.empno, this.ename);
	}
}

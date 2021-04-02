package com.tjoeun.collection;

import com.tjoeun.oop.Member;
import com.tjoeun.oop.Employee;
import com.tjoeun.oop.*;


public class OOPConcept {

	public static void main(String[] args) {
		/*
		 * VO: value object, 속성값만 존재하는 오브젝트
		 */
		
		Member a = new Member(11, "홍길동", "010-1111-0000", "hong@naver.com");
		Member aa = new Member(11, "홍길동", "010-1111-0000", "hong@naver.com");
		
		Employee emp = new Employee();
		emp.setEmpno(100);
		emp.setEname("홍길동");
		System.out.println(emp.toString());
		
		Shape[] shapelist = new Shape[3];
		shapelist[0] = new Circle(5);
		shapelist[1] = new Triangle(2, 4);
		shapelist[2] = new Rectangle(6, 7);
		
		for(int i = 0; i<shapelist.length; i++) {
			shapelist[i].getArea();
		}
	}

}

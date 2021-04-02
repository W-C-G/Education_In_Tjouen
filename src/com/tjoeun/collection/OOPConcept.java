package com.tjoeun.collection;

import com.tjoeun.oop.Member;
import com.tjoeun.oop.Employee;
import com.tjoeun.oop.*;


public class OOPConcept {

	public static void main(String[] args) {
		/*
		 * VO: value object, �Ӽ����� �����ϴ� ������Ʈ
		 */
		
		Member a = new Member(11, "ȫ�浿", "010-1111-0000", "hong@naver.com");
		Member aa = new Member(11, "ȫ�浿", "010-1111-0000", "hong@naver.com");
		
		Employee emp = new Employee();
		emp.setEmpno(100);
		emp.setEname("ȫ�浿");
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

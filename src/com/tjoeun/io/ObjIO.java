package com.tjoeun.io;

import java.io.*;
import java.util.*;
import com.tjoeun.oop.*;

public class ObjIO {

	public static void main(String[] args) {
		// ObjectInputStream, ObjectOutputStream
		/*
		 * ��ü�� ����ȭ(Serialization): ��ü�� ������ ��, ��Ʈ�� �ø���ȭ�Ͽ� ����.
		 * ������ȭ�� ����.
		 */
		
		String fpath = "C:/labs/objio.data";
		
		Member m = new Member(11, "Smith", "010-3567-2542", "smith@naver.com");
		try {
			ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(fpath));
			objout.writeObject(m);
			objout.close();
			
			ObjectInputStream objin = new ObjectInputStream(new FileInputStream(fpath));
			
			Member m2 = (Member)objin.readObject();
			m2.printList();
			objin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

package com.tjoeun.io;

import java.io.*;
import java.util.*;
import com.tjoeun.oop.*;

public class ObjIO {

	public static void main(String[] args) {
		// ObjectInputStream, ObjectOutputStream
		/*
		 * 객체의 직렬화(Serialization): 객체를 저장할 때, 비트를 시리얼화하여 저장.
		 * 비직렬화도 있음.
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

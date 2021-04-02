package com.tjoeun.io;

import java.io.*;
import java.util.*;

import com.tjoeun.oop.Member;

public class DataIO {
	static String fpath = "C:/labs/dataio.data";
	public static void main(String[] args) {
		// DataInputStream, DataOutputStream
		Member m = loadMem();
		m.printList();
		
		Member m2 = new Member(22, "Name", "010-2345-3225", "email");
		saveMem(m2);
		
		System.out.println("프로그램 종료..");
	}
	
	private static void saveMem(Member m) {
		try {
			
			DataOutputStream dout = new DataOutputStream(new FileOutputStream(fpath));
			dout.writeInt(m.getNumber());
			dout.writeUTF(m.getName());
			dout.writeUTF(m.getPhone());
			dout.writeUTF(m.getEmail());
			dout.close();
			
			System.out.println("[저장 완료]");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Member loadMem() {
		Member m = new Member();
		try {
			DataInputStream din = new DataInputStream(new FileInputStream(fpath));
			int num = din.readInt();
			String name = din.readUTF();
			String phone = din.readUTF();
			String email = din.readUTF();
			
			m.setNumber(num);
			m.setName(name);
			m.setPhone(phone);
			m.setEmail(email);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[로드 완료]");
		return m;
	}
	
}

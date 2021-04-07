package com.tjoeun.io;

import com.tjoeun.oop.Member;
import java.io.*;
import java.util.ArrayList;

public class ObjectStreamTest {

	public static void main(String[] args) {
		Member m = new Member(11, "Ward", "010-2356-3425", "ward@naver.com");
		Member m2 = new Member(22, "Ward", "010-2356-3425", "ward@naver.com");
		Member m3 = new Member(33, "Ward", "010-2356-3425", "ward@naver.com");
		
		String fpath = "C:/labs/mem.obj";
		
		// saveList, loadList
		ArrayList<Member> memlist = new ArrayList<>();
		memlist.add(m);
		memlist.add(m2);
		memlist.add(m3);
		saveMemlist(memlist, fpath);
		
		ArrayList<Member> newmemlist = loadMemlist(fpath);
		for(int i =0; i<newmemlist.size();i++) {
			newmemlist.get(i).printList();
		}
		
		// saveMem, loadMem Test
		saveMem(m, fpath);
		Member tmp = loadMem(fpath);
		tmp.printList();
		
		byte[] arr = saveMembyte(m);
		Member tmp2 = loadMembyte(arr);
		tmp2.printList();
		
		System.out.println("프로그램 종료...");
	}
	
	private static void saveMem(Member m, String fpath) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fpath));
			oos.writeObject(m);
			System.out.println("객체 직렬화 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 
	 * 파일에 저장된 Member 객체를 다시 메모리에 로드
	 */
	private static Member loadMem(String fpath) {
		Member m = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fpath));
			m = (Member) ois.readObject();
			return m;
		} catch (Exception e) {
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	private static void saveMemlist(ArrayList<Member> list, String fpath) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fpath));
			oos.writeObject(list);
			System.out.println("객체 리스트 직렬화 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/* 
	 * 파일에 저장된 Member List 객체를 다시 메모리에 로드
	 */
	private static ArrayList<Member> loadMemlist(String fpath) {
		ArrayList<Member> m = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fpath));
			m = (ArrayList<Member>) ois.readObject();
			return m;
		} catch (Exception e) {
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	private static byte[] saveMembyte(Member m) {
		ByteArrayOutputStream bao = null;
		ObjectOutputStream oos = null;
		byte[] arr = null;
		try {
			// ObjectOutputStream은 filter stream
			// ByteArrayOutputStream은 sink stream
			bao = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bao);
			oos.writeObject(m);
			oos.flush();
			System.out.println("객체 바이트 배열 저장 완료");
			arr = bao.toByteArray();
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	private static Member loadMembyte(byte[] arr) {
		ByteArrayInputStream bai = null;
		ObjectInputStream ois = null;
		Member tmp = null;
		try {
			// ObjectOutputStream은 filter stream
			// ByteArrayOutputStream은 sink stream
			bai = new ByteArrayInputStream(arr);
			ois = new ObjectInputStream(bai);
			tmp = (Member) ois.readObject();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tmp;
	}
}

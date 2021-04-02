import java.util.*;

public class Day2 {
	static String[] name = {"ȫ�浿", "��μ�", "��ö��"};
	static String[] phone = {"010-1111-0000", "010-2222-3333", "011-3344-5965"};
	static String[] email = {"hong@naver.com", "kim@naver.com", "lee@naver.com"};
	
	static String line = "================================================";

	public static void main(String[] args) {
		/**
		 * 1. ���α׷��� ���۵Ǹ� ������ ���� �޴��� ǥ�õȴ�.
		 * �߰� - a
		 * �˻� - f
		 * ���� - d
		 * ���� - u
		 * ��� - s 
		 * ���� - x
		 * 
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.println("���α׷��� �����մϴ�...");
		
		while(true) {
			
			System.out.println("[�߰� - a\t"
					+ "�˻� - f\t"
					+ "���� - d\t"
					+ "���� - u\t"
					+ "��� - s\t"
					+ "���� - x\t]");
			
			System.out.print("���ϴ� ���Ű�� �Է��Ͻÿ�: ");
			String key = sc.nextLine();
			switch(key){
				case "a":
					System.out.println("<�߰� ����� �����߽��ϴ�>");
					System.out.println("�̸�-��ȭ��ȣ-�̸��� ������ �Է��ϼ���.");
					
					String temp = sc.nextLine();
					String[] inarr = temp.split(" ");
					
					name = add(name, inarr[0]);
					phone = add(phone, inarr[1]);
					email = add(email, inarr[2]);
					
					System.out.println("[�߰� �Ϸ�]");
					
					break;
				case "f":
					System.out.println("<�˻� ����� �����߽��ϴ�>");
					
					System.out.println("��ȣ�� �˻��Ϸ��� 0��, �̸����� �˻��� ���� 1���� �����ּ���.");
					int check = Integer.parseInt(sc.nextLine());
					if(check == 0) {
						System.out.printf("�˻��� ������� ��ȣ�� �Է��ϼ���: ");
						
						int idx_2 = sc.nextInt();
						sc.nextLine();
						idx_2 -= 1;
						
						if(idx_2 > name.length) {
							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							break;
						}
						System.out.println(line);
						
						showByNum(idx_2);
						
					} else {
						System.out.printf("�˻��� ������� �̸��� �Է��ϼ���: ");
						
						String nameinfo = sc.nextLine();
						System.out.println(line);
		
						showByName(nameinfo);				
					}
					
					
					System.out.println();
					
					
					break;
				case "d":
					System.out.println("<���� ����� �����߽��ϴ�>");
					
					System.out.println("��ȣ�� �����Ϸ��� m, �̸����� ������ ���� n���� �����ּ���.");
					String key_d = sc.nextLine();
					switch(key_d) {
					case "m":
						System.out.printf("������ ��ȣ�� �Է��ϼ���: ");
						int delnum = Integer.parseInt(sc.nextLine());
						delnum -= 1;
						
						name = del(delnum, name);
						phone= del(delnum, phone);
						email = del(delnum, email);

						break;
					case "n":
						System.out.printf("������ �̸��� �Է��ϼ���: ");
						String delname = sc.nextLine();
						
						int idx_name = -1;
						for(int i=0; i<name.length ; i++) {
							if(name[i].equals(delname)) {
								idx_name = i;
							}
						}
						
						name = del(idx_name, name);						
						phone = del(idx_name, phone);
						email = del(idx_name, email);
						
						
						break;
					}
					
					
					System.out.println("[���� �Ϸ�]");
					
					break;
				case "u":
					System.out.println("<���� ����� �����߽��ϴ�>");
					
					System.out.printf("������ ������� ��ȣ�� �Է��ϼ���: ");
					int idx = sc.nextInt();
					sc.nextLine();
					idx -= 1;
					
					if(idx > name.length) {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						break;
					}
					System.out.printf("������ ������ �Է��ϼ���: ");
					String temp_email = sc.nextLine();
					
					email[idx] = temp_email;
					System.out.println("[����Ϸ�]");
					
					System.out.println(line);
					
					showByNum(idx);
					
					System.out.println();
					
					break;
				case "s":
					System.out.println("<��� ����� �����߽��ϴ�>");
					System.out.println(line);
					System.out.printf("��ȣ\t�̸�\t��ȭ��ȣ\t\t�̸���\n");
					for(int i = 0; i< name.length;i++) {
						System.out.printf("%d\t%s\t%s\t%s\t", i+1, name[i], phone[i], email[i]);
						System.out.println();
					}
					System.out.println();
					
					break;
				case "x":
					System.out.println("<���� ����� �����߽��ϴ�>");
					break;
				default: System.err.println("�޴� �Է� ����");
			}
			if(key.equals("x")) {
				System.out.println("���α׷��� �����մϴ�...");
				break;
			}
		}
	}
	
	
	public static String[] add(String[] input, String inarr) {
		String[] temp = new String[input.length+1];					
		System.arraycopy(input, 0, temp, 0, input.length);
		temp[temp.length - 1] = inarr;					
		input = temp;
		return input;
	}
	
	public static void showByNum(int idx) {
		System.out.printf("%d\t%s\t%s\t%s\t\n", idx+1, name[idx], phone[idx], email[idx]);
	}
	
	public static void showByName(String nameinfo) {
		int idx = 0;
		boolean check = false;
		for(int i = 0; i<name.length ; i++) {
			if(name[i].equals(nameinfo)) {
				idx = i;
				check = true;
				break;
			}
		}
		if(!check) {
			System.out.println("[�˻������ �����ϴ�]");
		}else {
			System.out.printf("%d\t%s\t%s\t%s\t\n", idx, name[idx], phone[idx], email[idx]);	
		}
		
	}
	
	public static String[] del(int inputnum, String[] input) {
		String[] temp = new String[input.length-1];
		for(int i=0, j = 0; i<temp.length; i++, j++) {
			if(j == inputnum) {
				j += 1;
			}
			temp[i] = input[j];
		}
		input = temp;
		return input;
	}
}

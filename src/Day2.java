import java.util.*;

public class Day2 {
	static String[] name = {"홍길동", "김민수", "이철수"};
	static String[] phone = {"010-1111-0000", "010-2222-3333", "011-3344-5965"};
	static String[] email = {"hong@naver.com", "kim@naver.com", "lee@naver.com"};
	
	static String line = "================================================";

	public static void main(String[] args) {
		/**
		 * 1. 프로그램이 시작되면 다음과 같은 메뉴가 표시된다.
		 * 추가 - a
		 * 검색 - f
		 * 삭제 - d
		 * 수정 - u
		 * 목록 - s 
		 * 종료 - x
		 * 
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.println("프로그램을 시작합니다...");
		
		while(true) {
			
			System.out.println("[추가 - a\t"
					+ "검색 - f\t"
					+ "삭제 - d\t"
					+ "수정 - u\t"
					+ "목록 - s\t"
					+ "종료 - x\t]");
			
			System.out.print("원하는 기능키를 입력하시오: ");
			String key = sc.nextLine();
			switch(key){
				case "a":
					System.out.println("<추가 기능을 선택했습니다>");
					System.out.println("이름-전화번호-이메일 순으로 입력하세요.");
					
					String temp = sc.nextLine();
					String[] inarr = temp.split(" ");
					
					name = add(name, inarr[0]);
					phone = add(phone, inarr[1]);
					email = add(email, inarr[2]);
					
					System.out.println("[추가 완료]");
					
					break;
				case "f":
					System.out.println("<검색 기능을 선택했습니다>");
					
					System.out.println("번호로 검색하려면 0번, 이름으로 검색할 때는 1번을 눌러주세요.");
					int check = Integer.parseInt(sc.nextLine());
					if(check == 0) {
						System.out.printf("검색할 사용자의 번호를 입력하세요: ");
						
						int idx_2 = sc.nextInt();
						sc.nextLine();
						idx_2 -= 1;
						
						if(idx_2 > name.length) {
							System.out.println("잘못 입력하셨습니다.");
							break;
						}
						System.out.println(line);
						
						showByNum(idx_2);
						
					} else {
						System.out.printf("검색할 사용자의 이름을 입력하세요: ");
						
						String nameinfo = sc.nextLine();
						System.out.println(line);
		
						showByName(nameinfo);				
					}
					
					
					System.out.println();
					
					
					break;
				case "d":
					System.out.println("<삭제 기능을 선택했습니다>");
					
					System.out.println("번호로 삭제하려면 m, 이름으로 삭제할 때는 n번을 눌러주세요.");
					String key_d = sc.nextLine();
					switch(key_d) {
					case "m":
						System.out.printf("삭제할 번호를 입력하세요: ");
						int delnum = Integer.parseInt(sc.nextLine());
						delnum -= 1;
						
						name = del(delnum, name);
						phone= del(delnum, phone);
						email = del(delnum, email);

						break;
					case "n":
						System.out.printf("삭제할 이름을 입력하세요: ");
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
					
					
					System.out.println("[삭제 완료]");
					
					break;
				case "u":
					System.out.println("<수정 기능을 선택했습니다>");
					
					System.out.printf("변경할 사용자의 번호를 입력하세요: ");
					int idx = sc.nextInt();
					sc.nextLine();
					idx -= 1;
					
					if(idx > name.length) {
						System.out.println("잘못 입력하셨습니다.");
						break;
					}
					System.out.printf("변경할 내용을 입력하세요: ");
					String temp_email = sc.nextLine();
					
					email[idx] = temp_email;
					System.out.println("[변경완료]");
					
					System.out.println(line);
					
					showByNum(idx);
					
					System.out.println();
					
					break;
				case "s":
					System.out.println("<목록 기능을 선택했습니다>");
					System.out.println(line);
					System.out.printf("번호\t이름\t전화번호\t\t이메일\n");
					for(int i = 0; i< name.length;i++) {
						System.out.printf("%d\t%s\t%s\t%s\t", i+1, name[i], phone[i], email[i]);
						System.out.println();
					}
					System.out.println();
					
					break;
				case "x":
					System.out.println("<종료 기능을 선택했습니다>");
					break;
				default: System.err.println("메뉴 입력 오류");
			}
			if(key.equals("x")) {
				System.out.println("프로그램을 종료합니다...");
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
			System.out.println("[검색결과가 없습니다]");
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

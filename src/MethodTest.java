import java.util.*;
import com.tjoeun.oop.*;

public class MethodTest {
	static Random rd = new Random(); 

	public static void main(String[] args) {
		
	// 문제 1
	int a = rd.nextInt(10)+1; 
	int b = rd.nextInt(10)+1;
	int[] result = add(a, b);
		
	// 문제 2
	int[] input1 = new int[3];
	input1[0] = rd.nextInt();
	input1[1] = rd.nextInt();
	input1[2] = rd.nextInt();
	int resultSum = sum(input1);

	// 문제 3
	String hi = greet("smith");

	// 문제 4
	int[] input2 = {2, 5, 6, 3, 2, 1};
	//System.out.println(Arrays.toString(evenlist(input2)));
	
	// 문제 5
	ArrayList<Integer> result3 = evenlist2(input2);
	/*
	 * for(int i =0;i<result3.size();i++) { System.out.println(result3.get(i)); }
	 */
	
	// 문제 6
	String password = password();
	
	// 문제 7
	System.out.println(sum2(5, 10));
	
	// 문제 8
	createM(1, "smith").printList();
	
	// 문제 9
	Member m1 = new Member(1, "smith", null, null);
	Member m2 = new Member(2, "bob", null, null);
	Member m3 = new Member(3, "kali", null, null);
	ArrayList<Member> memlist = new ArrayList<>();
	memlist.add(m1);
	memlist.add(m2);
	memlist.add(m3);
	String[] names = getNames(memlist);
	System.out.println(Arrays.toString(names));
	}

	/* 
	 * 무작위 정수를 2개 추출하여 add 메소드에 전달하고 add 메소드가 2개의 값을 배열에 
	 * 저장하여 리턴하는 기능
	*/
	private static int[] add(int a, int b) {
		int[] result = new int[2];
		result[0] = a;
		result[1] = b;
		
		return result;
	}
	
	/*
	 * 무작위 정수 3개로 구성된 배열을 sum 메서드에 전달하여 그 배열에
	 * 저장된 모든 원소의 합을 구하여 리턴하는 기능
	 */
	private static int sum(int[] inputlist) {
		int sum = 0;
		for(int i:inputlist) {
			sum+=i;
		}
		return sum;
	}
	
	/*
	 * greet 메소드에 회원의 이름을 전달하면 이름 뒤에 "안녕" 이 추가되어
	 * 리턴될 수 있도록 작성해보세요.
	 */
	private static String greet(String name) {
		return String.format("%s 안녕!!", name);
	}
	
	/*
	 * 어떤 메서드에 정수 배열을 전달하면 그 배열의 원소 중에서
	 * 짝수만 골라서 다시 배열을 생성하여 저장하고 배열을 리턴
	 */
	private static int[] evenlist(int[] inputlist) {
		int cnt =0;
		for(int i=0; i<inputlist.length; i++) {
			if(inputlist[i]%2==0) {
				cnt++;
			}
		}
		int[] result = new int[cnt];
		for(int i=0, j=0; i<inputlist.length; i++) {
			if(inputlist[i]%2==0) {
				result[j] = inputlist[i];
				j++;
			}
		}
		return result;
	}
	
	/*
	 * 어떤 메서드에 정수 배열을 전달하면 그 배열의 원소 중에서
	 * 짝수만 골라서 리스트 생성하여 저장하고 배열을 리턴
	 */
	private static ArrayList evenlist2(int[] inputlist) {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<inputlist.length; i++) {
			if(inputlist[i]%2==0) {
				result.add(inputlist[i]);
			}
		}
		return result;
	}
	/*
	 * 정수 97 -> 문자 'a'
	 * int n = 97;
	 * char c = (char)n;
	 * 무작위 수 10개를 구하여 모두 결합한 문자열을 리턴하는 메서드를 작성하고
	 * 호출해서 그 결과를 출력하고, 모음을 제외한 문자만 추출하여 문자열을 구성하여
	 * 리턴하는 메서드
	 */
	private static String password() {
		char[] tmp = new char[10];
		String password = new String();
		for(int i = 0;i<tmp.length;i++) {
			tmp[i] = (char)(rd.nextInt(26)+97);
		}
		String result = new String(tmp, 0, 10);
		
		System.out.println("Password: "+result);
		
		for(int i = 0;i<result.length();i++) {
			if(result.charAt(i) != 'a' && result.charAt(i) != 'e' &&
					result.charAt(i) != 'i' && result.charAt(i) != 'o'
					&& result.charAt(i) != 'u') {
				password += result.charAt(i);
			}
		}
		
		System.out.println("Password changed: "+ password);
		
		return password;
	}
	
	/*
	 * 임의의 두 정수를 전달받았을 때, 작은 수부터 큰 수에 이르기까지의 정수합을 구하는 기능
	 */
	private static int sum2(int a, int b) {
		int tmp = 0;
		int sum = 0;
		if(a > b) {
			tmp= a;
			a = b;
			b = tmp;
		}
		
		for(;a<=b ;a++) {
			sum += a;
		}
		return sum;
	}
	
	/*
	 * 회원번호, 회원명을 전달하여 Member 객체를 생성하여 리턴하는 기능
	 */
	private static Member createM(int num, String name) {
		Member mem = new Member();
		mem.setNumber(num);
		mem.setName(name);
		return mem;
	}
	
	/*
	 * 리스트 안에 3명의 회원 정보를 저장하여 어떤 메소드에 전달하고
	 * 전달받은 메소드에서는 그 회원들의 이름만 추출하여 리턴한다.
	 */
	private static String[] getNames(ArrayList<Member> memlist) {
		String[] names = new String[memlist.size()];
		for(int i =0; i<memlist.size(); i++) {
			names[i] = memlist.get(i).getName();
		}
		return names;
		
	}
}

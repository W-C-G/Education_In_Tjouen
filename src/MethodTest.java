import java.util.*;
import com.tjoeun.oop.*;

public class MethodTest {
	static Random rd = new Random(); 

	public static void main(String[] args) {
		
	// ���� 1
	int a = rd.nextInt(10)+1; 
	int b = rd.nextInt(10)+1;
	int[] result = add(a, b);
		
	// ���� 2
	int[] input1 = new int[3];
	input1[0] = rd.nextInt();
	input1[1] = rd.nextInt();
	input1[2] = rd.nextInt();
	int resultSum = sum(input1);

	// ���� 3
	String hi = greet("smith");

	// ���� 4
	int[] input2 = {2, 5, 6, 3, 2, 1};
	//System.out.println(Arrays.toString(evenlist(input2)));
	
	// ���� 5
	ArrayList<Integer> result3 = evenlist2(input2);
	/*
	 * for(int i =0;i<result3.size();i++) { System.out.println(result3.get(i)); }
	 */
	
	// ���� 6
	String password = password();
	
	// ���� 7
	System.out.println(sum2(5, 10));
	
	// ���� 8
	createM(1, "smith").printList();
	
	// ���� 9
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
	 * ������ ������ 2�� �����Ͽ� add �޼ҵ忡 �����ϰ� add �޼ҵ尡 2���� ���� �迭�� 
	 * �����Ͽ� �����ϴ� ���
	*/
	private static int[] add(int a, int b) {
		int[] result = new int[2];
		result[0] = a;
		result[1] = b;
		
		return result;
	}
	
	/*
	 * ������ ���� 3���� ������ �迭�� sum �޼��忡 �����Ͽ� �� �迭��
	 * ����� ��� ������ ���� ���Ͽ� �����ϴ� ���
	 */
	private static int sum(int[] inputlist) {
		int sum = 0;
		for(int i:inputlist) {
			sum+=i;
		}
		return sum;
	}
	
	/*
	 * greet �޼ҵ忡 ȸ���� �̸��� �����ϸ� �̸� �ڿ� "�ȳ�" �� �߰��Ǿ�
	 * ���ϵ� �� �ֵ��� �ۼ��غ�����.
	 */
	private static String greet(String name) {
		return String.format("%s �ȳ�!!", name);
	}
	
	/*
	 * � �޼��忡 ���� �迭�� �����ϸ� �� �迭�� ���� �߿���
	 * ¦���� ��� �ٽ� �迭�� �����Ͽ� �����ϰ� �迭�� ����
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
	 * � �޼��忡 ���� �迭�� �����ϸ� �� �迭�� ���� �߿���
	 * ¦���� ��� ����Ʈ �����Ͽ� �����ϰ� �迭�� ����
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
	 * ���� 97 -> ���� 'a'
	 * int n = 97;
	 * char c = (char)n;
	 * ������ �� 10���� ���Ͽ� ��� ������ ���ڿ��� �����ϴ� �޼��带 �ۼ��ϰ�
	 * ȣ���ؼ� �� ����� ����ϰ�, ������ ������ ���ڸ� �����Ͽ� ���ڿ��� �����Ͽ�
	 * �����ϴ� �޼���
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
	 * ������ �� ������ ���޹޾��� ��, ���� ������ ū ���� �̸�������� �������� ���ϴ� ���
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
	 * ȸ����ȣ, ȸ������ �����Ͽ� Member ��ü�� �����Ͽ� �����ϴ� ���
	 */
	private static Member createM(int num, String name) {
		Member mem = new Member();
		mem.setNumber(num);
		mem.setName(name);
		return mem;
	}
	
	/*
	 * ����Ʈ �ȿ� 3���� ȸ�� ������ �����Ͽ� � �޼ҵ忡 �����ϰ�
	 * ���޹��� �޼ҵ忡���� �� ȸ������ �̸��� �����Ͽ� �����Ѵ�.
	 */
	private static String[] getNames(ArrayList<Member> memlist) {
		String[] names = new String[memlist.size()];
		for(int i =0; i<memlist.size(); i++) {
			names[i] = memlist.get(i).getName();
		}
		return names;
		
	}
}

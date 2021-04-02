import java.util.*;

public class MethodTest {

	public static void main(String[] args) {
		
	// ���� 1
	Random rd = new Random(); 
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
	System.out.println(Arrays.toString(evenlist(input2)));
	
	// ���� 5
	ArrayList<Integer> result3 = evenlist2(input2);
	for(int i =0;i<result3.size();i++) {
		System.out.println(result3.get(i));
	}
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
}

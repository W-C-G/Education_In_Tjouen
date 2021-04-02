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
	System.out.println(resultSum);
		
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
}

import java.util.*;


public class MethodTest {

	public static void main(String[] args) {
		
	/* ������ ������ 2�� �����Ͽ� add �޼ҵ忡 �����ϰ� add �޼ҵ尡 2���� ���� �迭�� 
	 * �����Ͽ� �����ϴ� ���
	*/ 
	Random rd = new Random();
	int a = rd.nextInt(10)+1;
	int b = rd.nextInt(10)+1;
	int[] result = add(a, b);
		
	}
	
	private static int[] add(int a, int b) {
		int[] result = new int[2];
		result[0] = a;
		result[1] = b;
		
		return result;
	}
}

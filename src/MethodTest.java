import java.util.*;


public class MethodTest {

	public static void main(String[] args) {
		
	// 문제 1
	Random rd = new Random(); 
	int a = rd.nextInt(10)+1; 
	int b = rd.nextInt(10)+1;
	int[] result = add(a, b);
		
	// 문제 2
	int[] input1 = new int[3];
	input1[0] = rd.nextInt();
	input1[1] = rd.nextInt();
	input1[2] = rd.nextInt();
	
	int resultSum = sum(input1);
	System.out.println(resultSum);
		
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
}

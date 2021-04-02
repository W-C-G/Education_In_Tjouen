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

	// 문제 3
	String hi = greet("smith");

	// 문제 4
	int[] input2 = {2, 5, 6, 3, 2, 1};
	System.out.println(Arrays.toString(evenlist(input2)));
	
	// 문제 5
	ArrayList<Integer> result3 = evenlist2(input2);
	for(int i =0;i<result3.size();i++) {
		System.out.println(result3.get(i));
	}
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
}

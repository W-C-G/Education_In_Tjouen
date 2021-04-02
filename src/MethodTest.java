import java.util.*;


public class MethodTest {

	public static void main(String[] args) {
		
	/* 무작위 정수를 2개 추출하여 add 메소드에 전달하고 add 메소드가 2개의 값을 배열에 
	 * 저장하여 리턴하는 기능
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

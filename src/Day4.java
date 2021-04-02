import java.util.*;

public class Day4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		int a = rd.nextInt(100)+1;		
		int b = rd.nextInt(100)+1;
		
		add(a, b);
		
		multiply(a, b);

		greet("Smith");
		
		if(a>b) {
			int temp = a;
			a = b;
			b = a;
		}
		System.out.printf("%d ~ %d �ջ��� = %d\n", a, b, sum(a, b));
		
		int[] intArr = {1, 2, 3, 4, 5};
		
		System.out.println(sum(intArr));
		
		int[] arr = test();
		
		System.out.println(Arrays.toString(arr));
		
		int[] arr2 = new int[5];
		for(int i = 0 ; i< arr.length ; i++) {
			arr2[i] = rd.nextInt(30);
		}
		
		int[] arr3 = sort(arr2);
		System.out.println(Arrays.toString(arr3));
		
	}
	// add �޼ҵ� ����
	// �μ��� �հ� �� ���� ǥ���ϴ� ���
	public static void add(int a, int b) {
		System.out.printf("%d + %d = %d\n", a, b, a+b);
	}
	
	// mulitply �޼ҵ� ����
	// �μ��� �հ� �� ���� ǥ���ϴ� ���
	public static void multiply(int a, int b) {
		System.out.printf("%d * %d = %d\n", a, b, a*b);
	}
	
	public static void greet(String name) {
		System.out.println(name+"�� ȯ���մϴ�.");
	}
	
	public static int sum(int a, int b) {
		int sum = 0;
		for(int i=a;i<=b;i++) {
			sum += i;
		}	
		return sum;
	}
	
	// Overloading
	public static int sum(int[] arr) {
		int sum = 0;
		for(int i = 0; i<arr.length;i++) {
			sum += arr[i];
		}
		
		return sum;
	}
	
	// ������ ���� 5���� ������ �迭�� �����ϴ� �޼���
	// return�� �迭�� �޾Ƽ� ȭ�鿡 ǥ��
	public static int[] test() {
		Random rd = new Random();
		
		int[] arr = new int[5];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = rd.nextInt(30);
		}
		return arr;
	}
	
	// sort() ��� �Լ��� ���� �迭�� �����Ͽ�
	// �� �Լ��� ������ �� �� ���ĵ� �迭�� ����
	public static int[] sort(int[] arr) {		
		int temp = 0;
		int index = 0;
		
		// Selection Sort
		for(int i = 0; i < arr.length; i++) {
			int min = Integer.MAX_VALUE;			
			// Min value searching
			for(int j =i; j<arr.length; j++) {
				if(min > arr[j]) {
					min = arr[j];
					index = j;
				}
			}

			// Swap
			if(arr[i] > arr[index]) {
				temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
			}
		}
		
		return arr;
	}
}

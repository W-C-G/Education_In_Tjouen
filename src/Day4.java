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
		System.out.printf("%d ~ %d 합산결과 = %d\n", a, b, sum(a, b));
		
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
	// add 메소드 선언
	// 두수의 합과 그 식을 표시하는 기능
	public static void add(int a, int b) {
		System.out.printf("%d + %d = %d\n", a, b, a+b);
	}
	
	// mulitply 메소드 선언
	// 두수의 합과 그 식을 표시하는 기능
	public static void multiply(int a, int b) {
		System.out.printf("%d * %d = %d\n", a, b, a*b);
	}
	
	public static void greet(String name) {
		System.out.println(name+"님 환영합니다.");
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
	
	// 임의의 정수 5개를 저장한 배열을 리턴하는 메서드
	// return된 배열을 받아서 화면에 표시
	public static int[] test() {
		Random rd = new Random();
		
		int[] arr = new int[5];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = rd.nextInt(30);
		}
		return arr;
	}
	
	// sort() 라는 함수에 정수 배열을 선언하여
	// 그 함수가 정렬한 후 에 정렬된 배열을 리턴
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

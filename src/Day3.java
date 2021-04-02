import java.util.*;

public class Day3 {

	public static void main(String[] args) {		
		Random rd = new Random();
		
		// 반복문 연습(for, while) / 배열 생성, 활용 / 2차원 배열 / 선택 정렬 구현 / 
		
		/*
		// 임의 배열 생성
		for(int i =0;i<arr.length ; i++) {
			arr[i] = rd.nextInt(30);
		}
		
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
		
		for(int i =0;i<arr.length ; i++) {
			System.out.printf("%d\t", arr[i]);
		}
		*/
		
		/*
		int a = 3, b = 5;
		int cnt= 1;
		int[][] num = new int[a][b];
		int[] column = new int[b];
		int[] row = new int[a];
		for(int i = 0; i< a ; i++) {
			for(int j = 0; j< b ; j++) {
				row[i] += cnt;
				num[i][j] = cnt++;
			}
		}
		
		for(int i = 0; i<a ; i++) {
			for(int j = 0; j<b ; j++) {
				System.out.printf("%d ", num[i][j]);
			}
			System.out.println();
		}
		
		for(int i = 0; i<row.length;i++) {
			System.out.printf("%d ", row[i]);
		}
		*/
		
		
		
		
	}

}

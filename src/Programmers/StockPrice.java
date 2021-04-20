package Programmers;

import java.util.*;

/*
 * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 */
public class StockPrice {

	public static void main(String[] args) {
		Solution sol = new Solution();
		
		int[] a = {1, 2, 3, 2, 3}; //[4, 3, 1, 1, 0]
		for(var i:sol.solution(a))
			System.out.print(i+" ");
		
		System.out.println();
		int[] b = {1, 2, 3, 2, 3, 1};  // 5 4 1 2 1 0
		for(var i:sol.solution(b))
			System.out.print(i+" ");
		
	}

	static class Solution {
		class Stock{
			int time;
			boolean check = true;
			
			Stock(){
				this.time = 0;
				this.check = true;
			}
			public int getTime() {
				return time;
			}
			public void setTime(int time) {
				this.time = time;
			}
			public boolean getCheck() {
				return check;
			}
			public void setCheck(boolean check) {
				this.check = check;
			}
		}
		
	    public int[] solution(int[] prices) {
	        int[] answer = new int[prices.length];
	        
	        Stock[] list = new Stock[prices.length];
	        
	        for(int i=0;i<list.length;i++) {
	        	list[i] = new Stock();
	        }
	        
	        Queue<Integer> queue = new LinkedList<>();
	        for(int i=0;i<prices.length;i++) {
	        	if(queue.isEmpty()) {
	        		queue.add(prices[i]);
	        	}
	        	else {
		        	for(int j=0;j<i;j++) {
		        		if(prices[j] > prices[i] && list[j].getCheck()) {
		        			list[j].setCheck(false);
		        			answer[j]++;
		        		}
		        		else if((prices[j] <= prices[i]) && list[j].getCheck()) {
		        			answer[j]++;
		        		}
		        	}
		        	queue.add(prices[i]);
	        	}
	        }
	        
	        
	        return answer;
	    }
	}
}


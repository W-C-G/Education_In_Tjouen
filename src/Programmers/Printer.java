package Programmers;

import java.util.*;

/*
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.
예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.

내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 */
public class Printer {

	public static void main(String[] args) {
		Solution sol = new Solution();
		
		
		
		int[] b = {2, 1, 3, 2};
		System.out.println(sol.solution(b, 2));
		
	}

	static class Solution {
		class Paper{
			int importance;
			int index;
			
			Paper(){}
			Paper(int importance, int index){
				this.importance = importance;
				this.index = index;
			}
			public int getImportance() {
				return importance;
			}
			public void setImportance(int importance) {
				this.importance = importance;
			}
			public int getIndex() {
				return index;
			}
			public void setIndex(int index) {
				this.index = index;
			}
			
		}
		Queue<Paper> result = new LinkedList<>();
		
		public int solution(int[] priorities, int location) {
	        int answer = 1;
	        
	        Queue<Paper> list = new LinkedList<>();
	        for(int i=0;i<priorities.length;i++) {
	        	list.add(new Paper(priorities[i], i));
	        }
	        
	        
	        while(true) {
	        	if(list.size() == 2) {
	        		Paper tmp_c = list.poll();
	        		Paper tmp_d = list.poll();
	        		
	        		if(tmp_c.getImportance() >= tmp_d.getImportance()) {
	        			result.add(tmp_c);
	        			result.add(tmp_d);
	        		}
	        		else {
	        			result.add(tmp_d);
	        			result.add(tmp_c);
	        		}
	        		break;
	        	}
	        	max(list);
	        }
	        
	        
	        for(var i: result) {
	        	if(i.getIndex() == location) {
	        		break;
	        	}
	        	answer++;
	        }
	        
	        return answer;
	    }
		
		public void max(Queue<Paper> list) {
			int victory = 0;
	        
	        Paper tmp = list.poll();
	        Paper tmp_b = new Paper();
	        int time = list.size();
	        for(int i=0;i<time;i++) {
	        	tmp_b = list.poll();
	        	if(tmp.getImportance()>=tmp_b.getImportance())
		        	victory++;
	        	list.add(tmp_b);		        
	        }
	        if(victory == list.size()) {
	        	result.add(tmp);
	        }
	        else {
	        	list.add(tmp);
	        }
	        
		}
	}  	    
}


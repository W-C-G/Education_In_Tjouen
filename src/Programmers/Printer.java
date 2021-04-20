package Programmers;

import java.util.*;

/*
 * �Ϲ����� �����ʹ� �μ� ��û�� ���� ������� �μ��մϴ�. �׷��� ������ �߿��� ������ ���߿� �μ�� �� �ֽ��ϴ�. �̷� ������ �����ϱ� ���� �߿䵵�� ���� ������ ���� �μ��ϴ� �����͸� �����߽��ϴ�. �� ���Ӱ� ������ �����ʹ� �Ʒ��� ���� ������� �μ� �۾��� �����մϴ�.

1. �μ� ������� ���� �տ� �ִ� ����(J)�� ����Ͽ��� �����ϴ�.
2. ������ �μ� ����Ͽ��� J���� �߿䵵�� ���� ������ �� ���� �����ϸ� J�� ������� ���� �������� �ֽ��ϴ�.
3. �׷��� ������ J�� �μ��մϴ�.
���� ���, 4���� ����(A, B, C, D)�� ������� �μ� ����Ͽ� �ְ� �߿䵵�� 2 1 3 2 ��� C D A B ������ �μ��ϰ� �˴ϴ�.

���� �μ⸦ ��û�� ������ �� ��°�� �μ�Ǵ��� �˰� �ͽ��ϴ�. ���� ������ C�� 1��°��, A�� 3��°�� �μ�˴ϴ�.

���� ����Ͽ� �ִ� ������ �߿䵵�� ������� ��� �迭 priorities�� ���� �μ⸦ ��û�� ������ ���� ������� � ��ġ�� �ִ����� �˷��ִ� location�� �Ű������� �־��� ��, ���� �μ⸦ ��û�� ������ �� ��°�� �μ�Ǵ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.
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


package Programmers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Ʈ�� ���� �밡 ���� ���������� �� ���� �ٸ��� ������ ������ �ǳʷ� �մϴ�. 
 * ��� Ʈ���� �ٸ��� �ǳʷ��� �ּ� �� �ʰ� �ɸ����� �˾Ƴ��� �մϴ�. 
 * Ʈ���� 1�ʿ� 1��ŭ �����̸�, �ٸ� ���̴� bridge_length�̰� �ٸ��� ���� weight���� �ߵ��ϴ�.
 * 
 * �� Ʈ���� �ٸ��� ������ ������ ���� ���, �� Ʈ���� ���Դ� ������� �ʽ��ϴ�.
 * 
 */

public class DrivingTruck {
	public static void main(String[] args) {
		SolutionTruck tru = new SolutionTruck();
		int[] tmp = {7, 4, 5, 6};
		System.out.println(tru.solution(2, 10, tmp));
		
		int[] tmp1 = {10};
		System.out.println(tru.solution(100, 100, tmp1));
		
		int[] tmp2 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		System.out.println(tru.solution(100, 100, tmp2));
		
		
	}

	
}

class SolutionTruck {
	/*
	 * Ʈ�� Ŭ����
	 * weight: Ʈ���� ����
	 * check: �ٸ��� �ǳʴ� ����(��: �ٸ��� �ǳʱ� ���� 1, �ٸ��� �ǳʴ� ���� �� 1�� �����Ѵ�.)
	 */
	class truck{
		int weight;
		int check = 0;
		
		truck(){};
		truck(int weight, int check){
			this.weight = weight;
			this.check = check;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public int getCheck() {
			return check;
		}
		public void setCheck(int check) {
			this.check = check;
		}
		public void addCheck() {
			this.check++;
		}
	}
	
    public int solution(int bridge_length, int weight, int[] truck_weights) {
	    
	    // queue���� �Է¹��� ���������� Ʈ������ ����Ǿ� �ִ�. ex) 7, 4, 5, 6
	    Queue<truck> wait = new LinkedList<>();
	    for(var i:truck_weights) {
	    	wait.add(new truck(i, 0));
	    }
	    
	    // �ٸ��� �������� �� ing
	    Queue<truck> ing = new LinkedList<>();
	    
	    // �̹� �ٸ��� ������ done
	    Queue<truck> done = new LinkedList<>();
	    
	    // �ð� ����
	    int time = 0;
	   
	    while(true) {
	    	Iterator<truck> it = ing.iterator();
	    	/* 
	    	 * Ʈ���� �ٸ��� �ǳʴ� ���¸� Ȯ������ ��, �ٸ��� ���̿� ���ٸ� �������� ť(ing)���� ���ᰡ �� ť(done)�� �̵���Ų��.
	    	 * ConcurrentException �߻�
	    	 */
	    	if(!ing.isEmpty()) {
	    		while(it.hasNext()) {
	    			truck tr = null;
	    			try {
		    			tr = it.next();
		    			if(tr.getCheck()==bridge_length) {
		    				truck tmp = ing.poll();
		    				done.add(tmp);
		    			}
	    			} catch (Exception e) {
	    			} finally {
	    				break;
	    			}
	    		}
	    	}
	    	
	    	// ��� ���� Ʈ���� �������� Ʈ���� ����� ����
	    	if(wait.isEmpty()&&ing.isEmpty()) break;
	    		    	
	    	// �ƹ��� �ǳʴ� Ʈ���� ���ٸ� ���� ���� ť�� Ʈ�� ���� & ���� ���� ť�� Ʈ���� ���¸� ���� & �ð� ����
	    	if(ing.isEmpty()) {
	    		ing.add(wait.poll());
	    		add_all(ing);
	    		time++;
	    	}
	    	// �ǳʴ� Ʈ���� �ִٸ� 
	    	else {
	    		// ��� ���� Ʈ���� ���� ���
	    		if(!wait.isEmpty()) {
	    			// ��� ���� Ʈ���� �������� Ʈ���� ���԰� �ٸ��� ���Ը� ���� ���, ���� ���� Ʈ���� ���¸� ���� & �ð� ����
	    			if(wait.peek().getWeight()+driving_truck_weights(ing) > weight) {
	    				add_all(ing);
	    				time++;
	    			}
	    			// ��� ���� Ʈ���� �������� Ʈ���� ���԰� �ٸ��� ���Ը� ���� ���� ���, 
	    			// ��� ���� ť�� Ʈ�� ���� & ���� ���� Ʈ���� ���¸� ���� & �ð� ����
	    			else {
	    				ing.add(wait.poll());
	    				add_all(ing);
	    				time++;
	    			}
	    		}
	    		// ��� ���� Ʈ���� ���� ���, ���� ���� Ʈ���� ���¸� ���� & �ð� ����
	    		else {
	    			add_all(ing);
	    			time++;
	    		}
	    	}
	    }
	    // 1�ʺ��� �����ϱ� ������ 1 ����
	    return time+1;
    }
    
    // ť�� ����ִ� Ʈ������ ���� ��
	public int driving_truck_weights(Queue<truck> queue) {
		int result = 0;
		Iterator<truck> it = queue.iterator();
		while(it.hasNext()) {
			result += it.next().getWeight();
		}
		return result;
	}
	
	// ť�� ����ִ� Ʈ���� ���¸� 1�� ����
	public void add_all(Queue<truck> queue) {
		Iterator<truck> it = queue.iterator();
		while(it.hasNext()) {
			truck tr = it.next();
			tr.addCheck();
		}
	}
}
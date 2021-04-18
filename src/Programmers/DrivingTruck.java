package Programmers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다. 
 * 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 
 * 트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
 * 
 * ※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.
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
	    
	    // queue에는 입력받은 지나가려는 트럭들이 저장되어 있다. ex) 7, 4, 5, 6
	    Queue<truck> wait = new LinkedList<>();
	    for(var i:truck_weights) {
	    	wait.add(new truck(i, 0));
	    }
	    
	    
	    // 다리를 지나가는 중 ing
	    Queue<truck> ing = new LinkedList<>();
	    
	    // 이미 다리를 지나간 done
	    Queue<truck> done = new LinkedList<>();
	    
	    // 시간 측정
	    int time = 0;
	   
	    // 대기 중인 트럭과 지나가는 트럭이 없어야 종료
	    while(true) {
	    	Iterator<truck> it = ing.iterator();
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
	    	
	    	if(wait.isEmpty()&&ing.isEmpty()) break;
	    		    	
	    	if(ing.isEmpty()) {
	    		ing.add(wait.poll());
	    		add_all(ing);
	    		time++;
	    	}
	    	else {
	    		if(!wait.isEmpty()) {
	    			if(wait.peek().getWeight()+driving_truck_weights(ing) > weight) {
	    				add_all(ing);
	    				time++;
	    			}
	    			else {
	    				ing.add(wait.poll());
	    				add_all(ing);
	    				time++;
	    			}
	    		}
	    		else {
	    			add_all(ing);
	    			time++;
	    		}
	    	}
	    }
	    return time+1;
    }
    
	public int driving_truck_weights(Queue<truck> queue) {
		int result = 0;
		Iterator<truck> it = queue.iterator();
		while(it.hasNext()) {
			result += it.next().getWeight();
		}
		return result;
	}
	public void add_all(Queue<truck> queue) {
		Iterator<truck> it = queue.iterator();
		while(it.hasNext()) {
			truck tr = it.next();
			tr.addCheck();
		}
	}
}
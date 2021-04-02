import java.util.*;

public class Test_something {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello World!");
		
		Calendar cal = Calendar.getInstance();
		
		/*
		 * int year = cal.get(Calendar.YEAR); int mon = cal.get(Calendar.MONTH); int day
		 * = cal.get(Calendar.DAY_OF_MONTH); int hour = cal.get(Calendar.HOUR_OF_DAY);
		 * int dow = cal.get(Calendar.DAY_OF_WEEK); int min = cal.get(Calendar.MINUTE);
		 * int sec = cal.get(Calendar.SECOND);
		 * 
		 * String sDow = null;
		 * 
		 * System.out.println(dow);
		 * 
		 * int cnt = 20; while(cnt > 0) { if(cnt%2 == 0) System.out.printf("%d ", cnt);
		 * cnt--; } System.out.println(); int cnt2 = 1; int sum = 0; while(cnt2 <= 20) {
		 * if(cnt2%2==1) sum += cnt2; cnt2++; } System.out.println(sum);
		 * 
		 * sum = 0; Random rd = new Random();
		 * 
		 * while(sum<100) { int rand = rd.nextInt(100); System.out.println(rand);
		 * sum+=rand; }
		 */
		
		Scanner sc = new Scanner(System.in);
		
		/*
		int cnt;
		int n = 1;
		
		while(true) {
			cnt = 2;
			while(true) {
				System.out.printf("%d * %d = %d\t", cnt, n, cnt*n);
				cnt++;
				if (cnt == 9) break;
			}
			n++;
			System.out.println();
			if (cnt ==9 && n > 9) break;
		}
		*/
		/*
		String uid = new String("smith");
		String upw = new String("1234");
		
		while(true) {
			System.out.print("ID: ");
			String tempid = sc.nextLine();
			
			System.out.print("Password: ");
			String temppd = sc.nextLine();
			
			if(uid.equals(tempid) && upw.equals(temppd)) break;
		}
		*/
		
		
		/*
		 * int i = 1; do { if(i%7 == 0) System.out.printf("%d ", i); i++; }
		 * while(i<100);
		 */
		/*
		Random rd = new Random();
		int fac = rd.nextInt(9)+2;
		System.out.println(fac);
		int result = 1;
		for(; fac>0 ; --fac) {
			result *= fac;
		}
		System.out.println(result);
		*/
		
		// ------
		String uid = new String("smith");
		String upw = new String("1234");
		boolean check = false;
		
		for(int i = 0 ; i < 3 ; i++) {
		System.out.print("ID: ");
		String tempid = sc.nextLine();
		
		System.out.print("Password: ");
		String temppd = sc.nextLine();
		
		if(uid.equals(tempid) && upw.equals(temppd)) {
			check = true;
			break;
		}
		System.out.println("오류가 발생했습니다. 다시 입력해주세요.");
		}
		
		if(check) {
			System.out.println("로그인에 성공했습니다!");
		}else {
			System.out.println("오프라인 창구를 이용해주세요.");
		}
	}
}

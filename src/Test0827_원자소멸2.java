import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test0827_원자소멸2 {
	
	static int N;
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}}; //상하좌우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		
		for(int test=1; test<=T; test++) {
			
			N = Integer.parseInt(bf.readLine());
			int answer =0;
			ArrayList<int[]> arr = new ArrayList<>();
			
			for(int l=0; l<N; l++) {
				tk = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(tk.nextToken());
				int y = Integer.parseInt(tk.nextToken());
				int d = Integer.parseInt(tk.nextToken());
				int k = Integer.parseInt(tk.nextToken());
				
				arr.add(new int[] {x, y, d, k});
			}
			
//			while(true) {
//				
//				for(int i=0; i<arr.size(); i++) { //한번 각자 방향으로 이동 
//					int[] t = arr.get(i);
//					t[0] = t[0]+dir[t[2]][0];
//					t[1] = t[1]+dir[t[2]][1];
//					arr.set(i, new int[] {t[0], t[1], t[2], t[3]});
//				}
//				
//				ArrayList<Integer> del = new ArrayList<>();
//				for(int i=0; i<arr.size()-1; i++) {
//					int[] t = arr.get(i);
//					for(int j=i+1; j<arr.size(); j++) {
//						int[] t2 = arr.get(j);
//						
//						if(t[0]==t2[0] && t[1]==t2[1]) { //충돌한 원자가 있으면ㅇ
//							answer += (t[3] + t2[3]);
//							del.add(i);
//							del.add(j);
//						}
//					}
//				}
//				
//				for(int i=0; i<del.size(); i++) {
//					arr.remove(del.get(i));
//				}
//				
//			
//			}
			
			
			System.out.println("#"+test+" "+ answer);
			
			
			
		}
		

	}

}

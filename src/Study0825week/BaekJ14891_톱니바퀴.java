package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ14891_톱니바퀴 {
	
	public static int[] turn(int dir, int[] arr) {
		int[] answer = new int[arr.length];
		if(dir==-1) {
			answer[arr.length-1]= arr[0];
			for(int i=0; i<arr.length-1; i++) {
				answer[i]=arr[i+1];
			}
		}else if(dir==1) {
			answer[0]=arr[arr.length-1];
			for(int i=0; i<arr.length-1; i++) {
				answer[i+1]=arr[i];
			}
		}
		return answer;
	}
	

	public static void main(String[] args) throws IOException {
		//극이다른게 만나면 서로 반대방향으로 회전 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheel = new int[4][8];
		
		for(int i=0; i<4; i++) {
			String str = bf.readLine();
			for(int j=0; j<8; j++) {
				wheel[i][j]=str.charAt(j) -'0'; //N:0, S:1
			}
		}
		
		int K = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		for(int i=0; i<K; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken()); //1:시계, -1:반시계
			//시계일때 뒤로한칸씩 밀기, 반시계일때 앞으로 한칸씩 밀기 
			a--;
			
			//어디까지 돌릴지 먼저 계산하고 돌림 
			boolean c1 = wheel[0][2] != wheel[1][6];
			boolean c2 = wheel[1][2] != wheel[2][6];
			boolean c3 = wheel[2][2] != wheel[3][6];
			
			
			wheel[a] = turn(b, wheel[a]); //현재 톱니 돌리기 
			//c1, c2, c3 true면 둘다 돌아가야한다.
			if(a==0) {
				if(c1==true) {
					wheel[1] = turn(-b, wheel[1]);
					if(c2==true) {
						wheel[2] = turn(b, wheel[2]);
						if(c3==true) {
							wheel[3] = turn(-b, wheel[3]);
						}
					}
				}
				
			}else if(a==1) {
				if(c1==true) {
					wheel[0] = turn(-b, wheel[0]);
				}
				if(c2==true) {
					wheel[2] = turn(-b, wheel[2]);
					if(c3==true) {
						wheel[3] = turn(b, wheel[3]);
					}
				}
				
			}else if(a==2) {
				if(c2==true) {
					wheel[1] = turn(-b, wheel[1]);
					if(c1==true) {
						wheel[0] = turn(b, wheel[0]);
					}
				}if(c3==true) {
					wheel[3] = turn(-b, wheel[3]);
				}
			}else if(a==3) {
				if(c3==true) {
					wheel[2] = turn(-b, wheel[2]);
					if(c2==true) {
						wheel[1] = turn(b, wheel[1]);
						if(c1==true) {
							wheel[0] = turn(-b, wheel[0]);
						}
					}
				}
				
			}
		}
		
		int answer=0;
		if(wheel[0][0]==1) answer++;
		if(wheel[1][0]==1) answer+=2;
		if(wheel[2][0]==1) answer+=4;
		if(wheel[3][0]==1) answer+=8;
		
		System.out.println(answer);
		
	}
}

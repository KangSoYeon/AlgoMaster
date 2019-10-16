package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution4013_특이한자석 {
	static int arr[][] , K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		for(int test=1; test<=T; test++) {
			
			K = Integer.parseInt(bf.readLine());
			arr = new int[4][8];
			int answer = 0;
			for(int i=0; i<4; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<8; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
		
			for(int i=0; i<K; i++) {
				tk = new StringTokenizer(bf.readLine());
				int wn = Integer.parseInt(tk.nextToken()); //자석의번호
				int dir = Integer.parseInt(tk.nextToken()); //시계 :1, 반시계:-1
				boolean[] place = new boolean[4]; //세가지 장소의 회전 해야하는지 여부
				wn--; //내 인덱스에 맞게 바꾸기
				
				if(wn==0) {
					if(arr[0][2] != arr[1][6]) {
						place[0] = true;
						place[1] = true;
						if(arr[1][2] != arr[2][6]) {
							place[2] = true;
							if(arr[2][2] != arr[3][6]) {
								place[3] = true;
							}
						}
					}
				}else if(wn==1) {
					if(arr[0][2] != arr[1][6]) {
						place[0] = true;
						place[1] = true;
					}
					if(arr[1][2] != arr[2][6]) {
						place[1] = true;
						place[2] = true;
						if(arr[2][2] != arr[3][6]) {
							place[3] = true;
						}
					}
					
				}else if(wn==2) {
					if(arr[1][2] != arr[2][6]) {
						place[1] = true;
						place[2] = true;
						if(arr[0][2] != arr[1][6]) {
							place[0] = true;
						}
					}
					if(arr[2][2] != arr[3][6]) {
						place[2] = true;
						place[3] = true;
					}
				}else {
					if(arr[2][2] != arr[3][6]) {
						place[2] = true;
						place[3] = true;
						if(arr[1][2] != arr[2][6]) {
							place[1] = true;
							if(arr[0][2] != arr[1][6]) {
								place[0] = true;
							}
						}
					}
				}
				
							
				turn(wn, dir); //현재꺼 돌리고 
				
				//바로 양옆
				if(wn-1>=0 && place[wn-1]) { //왼쪽 휠도 돌아가야하면 
					turn(wn-1, -dir);
				}
				if(wn+1<4 && place[wn+1]) {
					turn(wn+1, -dir);
				}
				
				//한칸 띠고 양옆 
				if(wn-2>=0 && place[wn-2]) { //왼쪽 휠도 돌아가야하면 
					turn(wn-2, dir);
				}
				if(wn+2<4 && place[wn+2]) {
					turn(wn+2, dir);
				}
				
				//두칸 띠고 양옆 
				if(wn-3>=0 && place[wn-3]) { //왼쪽 휠도 돌아가야하면 
					turn(wn-3, -dir);
				}
				if(wn+3<4 && place[wn+3]) {
					turn(wn+3, -dir);
				}
				
				
			}
			
			for(int i=0; i<4; i++) {
				if(arr[i][0] == 1) { //s극일 때
					if(i==0) answer++;
					if(i==1) answer+=2;
					if(i==2) answer+=4;
					if(i==3) answer+=8;
				}
			}
			
			
			System.out.println("#"+test+" "+answer);
		}
	}

	private static void turn(int wn, int dir) { //wn번째 바퀴를 dir방향으로 한칸 돌리기
		if(dir==1) { //시계
			int temp = arr[wn][7];
			for(int i=6; i>=0; i--) {
				arr[wn][i+1]= arr[wn][i]; //하나씩 뒤로 미루고
			}
			arr[wn][0] = temp; //첫번째꺼 넣어주기 
			
		}else { //시계
			int temp = arr[wn][0];
			for(int i=0; i<7; i++) {
				arr[wn][i]= arr[wn][i+1];
			}
			arr[wn][7]=temp;
		}
		
	}

}

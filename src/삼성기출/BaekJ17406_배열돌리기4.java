package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJ17406_배열돌리기4 {
	static int N, M, K;
	static int[][] arr, save, cal;
	static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1, 0}};
	static int[] order;
	
	public static void turn(int sx, int sy, int ex, int ey) {
		int c = (ex-sx+1)/2; //돌리는 횟수 
		int[] temp = new int[4];
		temp[0] = sx;
		temp[1] = sy;
		temp[2] = ex;
		temp[3] = ey;
		
		for(int i=1; i<=c; i++) {
			for(int k=0; k<dir.length; k++) {
				while(true) {
					int nx = temp[0] + dir[k][0];
					int ny = temp[1] + dir[k][1];
					
					if(nx>ex-i+1 || ny>ey-i+1 || nx<sx+i-1 || ny<sy+i-1) break;
					
					arr[nx][ny] = save[temp[0]][temp[1]]; //옮기기 
					
					temp[0]= nx;
					temp[1]= ny;	
				}
			}
			temp[0] = sx+i;
			temp[1] = sy+i;
			temp[2] = ex-i;
			temp[3] = ey-i;			
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		save = new int[N][M];
		int[][] ssave = new int[N][M];
		cal = new int[K][];
		order = new int[K];
		
		for(int i=0; i<K; i++) {
			order[i]=i;
		}
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
				save[i][j] = arr[i][j];
				ssave[i][j] = arr[i][j];
			}
		}
		
		for(int i=0; i<K; i++) {
			tk = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(tk.nextToken());
			int c = Integer.parseInt(tk.nextToken());
			int s = Integer.parseInt(tk.nextToken());
			
			cal[i] = new int[] {r, c, s}; 
		}
		
		int min = Integer.MAX_VALUE;
		int sx, sy, ex, ey;
		do {
			int answer =0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					arr[i][j] = ssave[i][j]; //완전 초기값으로 돌리기 
					save[i][j] = ssave[i][j];
				}
			}

			for(int z = 0; z<order.length; z++) {
				int[] tt = cal[order[z]]; 
				sx = tt[0]-tt[2]-1;
				sy = tt[1]-tt[2]-1;
				ex = tt[0]+tt[2]-1;
				ey = tt[1]+tt[2]-1;
				
				//연산규칙에 맞게 돌리기 
				//연속으로 했던 배열에 또 돌리는 거임 
				
				turn(sx, sy, ex, ey);
				
				for(int ii=0; ii<N; ii++) {
					for(int j=0; j<M; j++) {
						save[ii][j]=arr[ii][j]; //save update
					}
				}
			} //하나의 케이스로 해본 최솟값 
			
			for(int i=0; i<N; i++) {
				answer =0;
				for(int j=0; j<M; j++) {
					answer += arr[i][j];
				}
				min = Math.min(min, answer);
			}
			
		}while(next_permutation());
		System.out.println(min);
		
	}

	private static boolean next_permutation() {
		//0부터 K-1까지 줄세우기 
		int i = K-1;
		while(i>0 && order[i]<=order[i-1]) i--;
		
		if(i==0) return false;
		
		int j=K-1;
		while(order[j] <= order[i-1]) j--;
		
		swap(i-1, j);
		
		j = K-1;
		while(j>i) swap(i++, j--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = order[i];
		order[i]=order[j];
		order[j]=temp;
	}
}

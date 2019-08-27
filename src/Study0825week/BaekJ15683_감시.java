package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJ15683_감시 {
	
	static int N, M;
	static int[][] arr;
	
	
	public static void color(int[] c) {
		int[][] dir = null;
		if(c[2]==5) {
			dir = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
		}else if(c[2]==4) {
			
		}else if(c[2]==3) {
			
		}else if(c[2]==2) {
			
		}else if(c[2]==1) {
			
		}
		
		int nx=0, ny=0;
		int mul=1;
		for(int k=0; k<dir.length; k++) {
			while(true) {
				nx = c[0] + (dir[k][0]*mul);
				ny = c[1] + (dir[k][1]*mul);
				
				if(nx>=0 && nx<N && ny>=0 && ny<M
						&& arr[nx][ny] != 6) {
					if(arr[nx][ny] != 0) continue;
					arr[nx][ny] = 9; //레이저 닿은곳을 9로 바꾸기 
				}
				mul++;
			}
			
		}
	
		
		
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		ArrayList<int[]> cctv = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
				if(arr[i][j] != 6 && arr[i][j] != 0) {
					cctv.add(new int[] {i, j, arr[i][j]}); //레이저의 좌표와, 어떤레이저인지 넣음 
				}
			}
		}
		

		//1 : 4가지 경우
		//2 : 2가지 경우
		//3 : 4가지 경우
		//4 : 4가지 경우
		//5 : 1가지 경우
		
		
		
		

	}

}

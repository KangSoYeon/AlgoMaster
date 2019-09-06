package class0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1220_Magnetic {
	
	static int[][] arr, stuck;
	static int N;
	static boolean[][] check;
	static int[][] dir = {{1,0}, {-1,0}};
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		check[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] t = q.poll();
			
			for(int k=0; k<dir.length; k++) {
				int nx = t[0] + dir[k][0];
				int ny = t[1] + dir[k][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N
						&& stuck[nx][ny]==1 && !check[nx][ny]) {
					q.offer(new int[] { nx, ny});
					check[nx][ny]= true;
				}
			}	
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
	
		
		for(int test=1; test<=10; test++) {
			
			N = Integer.parseInt(bf.readLine());
			arr = new int[N][N]; 
			stuck = new int[N][N];
			check = new boolean[N][N]; 
			Queue<int[]> mag = new LinkedList();
			
			int count = 0;
			
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) { //mag수집 
				for(int j=0; j<N; j++) {
					if(arr[i][j]==1) mag.add(new int[] {i,j, 1});
					else if(arr[i][j]==2) mag.add(new int[] {i,j, 2});
				}
			}
		
		
			while(!mag.isEmpty()) {
				int[] t = mag.poll();
				
				if(t[2]==2) { //S극 --> 위로가야함
					if(t[0]-1<=0) {
						arr[t[0]][t[1]] = 0; //사라짐
					}else if(arr[t[0]-1][t[1]]==0) { //위에 한칸이 빈공간이면 
						mag.offer(new int[] {t[0]-1, t[1], 2}); //위로 올린걸로 다시놓기, 우선순위는 뒤로밀림 
						arr[t[0]][t[1]] = 0; //이동
						arr[t[0]-1][t[1]] = 2;
					}else {
						if(stuck[t[0]][t[1]]==0)
							stuck[t[0]][t[1]] = 2; //더이상 이동할수 없으면  stuck배열에 표시 
					}
				}else if(t[2]==1) { //N극 --> 아래로가야함 
					if(t[0]+1 == N) { //이러면 사라짐 
						arr[t[0]][t[1]] = 0; //사라짐 
					}else if(arr[t[0]+1][t[1]]==0) {
						mag.offer(new int[] {t[0]+1, t[1], 1});
						arr[t[0]][t[1]] = 0; //이동
						arr[t[0]+1][t[1]] = 1;
					}else {
						if(stuck[t[0]][t[1]]==0)
							stuck[t[0]][t[1]] =1;
					}			
				}
			}
			
			
			
			
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(stuck[i][j]+" ");
//				}
//				System.out.println();
//			}
			
//			int j=0;
//			for(int i=0; i<N; i++){
//				j=0;
//				while(++j<N) {
//					if(stuck[j][i]==1) { //N극을 만났는데 다음극이 S극이면 
//						while(++j<N) {
//							if(stuck[j][i]==2) {
//								count++;
//								break;
//							}
//						}
//					}	
//				}
//			}
				
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]==1 && !check[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			
			System.out.println("#"+test+" "+count); 
			
			
		}
		
	}

}

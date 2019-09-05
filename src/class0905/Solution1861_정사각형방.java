package class0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1861_정사각형방 {
	
	static int N, count;
	static int[][] arr;
	static int[][] dir = { {1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void dfs(int x, int y) { //해당 좌표에서 가장 많이 갈수 있는 좌표 구하기 

		for(int i=0; i<dir.length; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(nx>=0 && ny>=0 && nx<N && ny<N
					&& arr[nx][ny] == arr[x][y]+1) {
				dfs(nx, ny);
				count++;
			}
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(bf.readLine());
			arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					count = 0;
					dfs(i,j); //이지점에서 갈수 있는 가장 많은 수 
					if(count > max) { 
						//count 가장 큰 값으로, arr가장 작은 값으로
						max = count;
						min = arr[i][j];
					}else if(count==max) { //같을 때는 arr[i][j]가 작으면 바꾸기 
						if(arr[i][j]<min) {
							min = arr[i][j];
						}
					}
				}
			}
			max++;
			
			System.out.println("#"+test+" "+ min+" "+ max);
		}
	}
}

package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ2636_치즈 {
	static int N, M;
	static int[][] arr, check;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void bfsA(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int i=0; i<dir.length; i++) {
				int nx = temp[0] + dir[i][0];
				int ny = temp[1] + dir[i][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<M &&
						arr[nx][ny] == 0 && check[nx][ny]==0) {
					q.offer(new int[] {nx, ny});
					check[nx][ny] = 3; 
					arr[nx][ny] = 3; //공기를 3으로 칠함 
				}
			}
		}
	}
	
	public static void bfsC(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int i=0; i<dir.length; i++) {
				int nx = temp[0] + dir[i][0];
				int ny = temp[1] + dir[i][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<M &&
						arr[nx][ny] == 1 && check[nx][ny]==0) {
					q.offer(new int[] {nx, ny});
					check[nx][ny] = 1; 
					arr[nx][ny] = 3; //공기를 3으로 칠함 
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		check = new int[N][M];
		int count=0; //치즈갯수 저장 
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
				if(arr[i][j]==1) count++;
			}
		}

		//공기인 부분을 체크해서 칠하기  
		bfsA(0,0); //0,0과 맞닿은 0들은 모두 공기임 
		check = new int[N][M]; //check 초기화 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==3) { //공기인데   
					for(int k=0; k<dir.length; k++) {
						int nx = i + dir[i][0];
						int ny = j + dir[i][1];
					
						if(nx>=0 && ny>=0 && nx<N && ny<M 
								&& arr[nx][ny]==1 && check[nx][ny]==0) { //방향검색시 치즈면 
							arr[nx][ny]=3; //치즈가 녹았음 
							check[nx][ny]=1; 
							count--;
							break; //한방향이라도 공기이면 되니까 더이상 갈필요 없음 
						}
					}
				}
			}
		}
		
		
		
		
		
		
		
	}

}

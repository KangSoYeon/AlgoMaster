package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main1249_보급로 {
	
	static int[][] dir = { {1,0}, {-1,0}, {0,1}, {0,-1}};
	static int N;
	static int[][] arr;
	static int[][] check;
	//단일 출발지에서 단일 목적지에 가는 최단경로
	//최단경로는 bfs가 유리 
	static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() { 
			//priority큐에 생성자에 comparator 준다 --> 생성할 때 바로바로 정렬하니까 
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		q.offer(new int[] {0, 0, arr[0][0]});
		check[0][0] = arr[0][0];
		
		while(!q.isEmpty()) {
			int[] t= q.poll();
			
			if(t[0] == N-1 && t[1] == N-1) return; //priority큐니까 할수 있는거 
			
			for(int d=0; d<4; d++) {
				int nx = t[0] + dir[d][0];
				int ny = t[1] + dir[d][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N
						&& check[nx][ny] > t[2] + arr[nx][ny]) { //먼저 다녀간 애보다 앞으로 올애가 더 복구시간이 짧으면
					check[nx][ny] = arr[nx][ny]+t[2];
					q.offer(new int[] {nx, ny, check[nx][ny]});
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(bf.readLine());
			arr =new int[N][N];
			check = new int[N][N];
			for(int i=0; i<N; i++) {
				String str= bf.readLine();
				for(int j=0; j<N; j++) {
					arr[i][j] = str.charAt(j)-'0';
					check[i][j] = Integer.MAX_VALUE; //가장 큰갑 ㅅ넣어놓기
				}
			}
			
			bfs();
			System.out.println("#"+test+" "+check[N-1][N-1]);
		}	
	}
}
